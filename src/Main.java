import java.util.ArrayList;

public final class Main {
    private static ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private static ArrayList<Reserva> listaEspera = new ArrayList<Reserva>();

    public static void main(String[] args) {
        boolean exit = false;
        Processo.ajudaInicial();

        while(!exit){
            try {
                Processo.userPointer();
                String input = Processo.getStrInput();
        
                if ("".equals(input)) {
                } else if ("0".equals(input)) {
                    Processo.help();
                } else if ("1".equals(input)) {
                    reservar_mesa();
                } else if ("2".equals(input)) {
                    possui_reserva(Processo.informar_codigo(Processo.escolherCliente()));
                } else if ("3".equals(input)) {
                    Processo.imprimir_lista(reservas);
                } else if ("4".equals(input)) {
                    Processo.imprimir_lista(listaEspera);
                } else if ("5".equals(input)) {
                    cancelar_reserva();
                } else if ("6".equals(input)) {
                    exit = true;
                }else {
                    Processo.opInvalida();
                    throw new UnsupportedOperationException();
                }
            } catch (UnsupportedOperationException e) {
                e.printStackTrace();
            }
        }
    }

    private static void reservar_mesa(){
        Cliente novo_cliente = null;
        TpPessoa tp_c = Processo.escolherCliente();
        String nome = Processo.inserirNome();

        switch (tp_c) {
            case FISICA:
                String cpf = Processo.informar_codigo(TpPessoa.FISICA);
                if (!codigoJaExiste(cpf)){
                    PessoaFisica pf = new PessoaFisica(nome, cpf);
                    novo_cliente = pf;
                } else {
                    Processo.jaCadastrado(tp_c);
                }
                break;
            case JURIDICA:
                String cnpj = Processo.informar_codigo(TpPessoa.JURIDICA);
                if (!codigoJaExiste(cnpj)) {
                    PessoaJuridica pj = new PessoaJuridica(nome, cnpj);
                    novo_cliente = pj;
                }
                else {
                    Processo.jaCadastrado(tp_c);
                }
                break;
        }

        TpPagamento tp_p = Processo.escolherPagamento();
        boolean pagamentoAVista;
        switch (tp_p) {
            case AVISTA:
                pagamentoAVista = true;
                break;
            case PARCELADO:
                pagamentoAVista = false;
                break;
            default:
                pagamentoAVista = false;
        }

        Reserva nova_reserva = new Reserva(novo_cliente, pagamentoAVista);
        if( reservas.size() < 7) {
            reservas.add(nova_reserva);
        } else {
            listaEspera.add(nova_reserva);
        }
    }

    private static Reserva pesquisar_reserva(String codigo){

        Reserva reservaEncontrada = null;
        for (Reserva reserva : reservas) {
            if (reserva.cliente instanceof PessoaFisica) {
                PessoaFisica cliente = (PessoaFisica) reserva.cliente;
                if (cliente.getCpf().equals(codigo)){
                    reservaEncontrada = reserva;
                }
            } else {
                PessoaJuridica cliente = (PessoaJuridica) reserva.cliente;
                if (cliente.getCnpj().equals(codigo)){
                    reservaEncontrada = reserva;
                }
            }
        }
        return reservaEncontrada;
    }
    
    private static boolean codigoJaExiste(String codigo){
        if (pesquisar_reserva(codigo) == null) {
            return false;
        }
        return true;
    }

    private static void possui_reserva(String codigo){
        Reserva reservaEncontrada = pesquisar_reserva(codigo);
        Processo.possuiReserva(reservaEncontrada);
    }

    private static void cancelar_reserva(){
        String codigo = Processo.informar_codigo(Processo.escolherCliente());

        Reserva reserva = pesquisar_reserva(codigo);
        if (!reserva.equals(null)){
            reservas.remove(reserva);
            Processo.reservaCancelada();
        }
    }

}

