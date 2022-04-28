import java.util.ArrayList;
import java.util.Scanner;

public class Processo {
    static Scanner scanner = new Scanner(System.in);

    public static String screen() {
		String aux = "Restaurante SABOR SOFISTICADO:\n";
		aux += "1. Reservar mesa\n";
		aux += "2. Pesquisar reserva\n";
		aux += "3. Imprimir reservas\n";
		aux += "4. Imprimir lista de esperas\n";
		aux += "5. Cancelar reserva\n";
		aux += "6. Finalizar\n";
		return aux;
		
	}
    

    public static void userPointer() {
        System.out.print("User> ");
    }

    public static String getStrInput() {
        return scanner.nextLine().trim().toLowerCase();
    }


    public static void jaCadastrado(TpPessoa tp_c){
        switch (tp_c){
            case FISICA:
                System.out.println("Cpf ja Cadastrado!");
                break;
            case JURIDICA:
                System.out.println("Cnpj ja Cadastrado!");
                break;
        }
    }

    public static String inserirNome() {
        System.out.print("Nome: ");
        String nome = getStrInput();
        return nome;
    }

    public static void possuiReserva(Reserva reservaEncontrada) {
        if (reservaEncontrada == null){
            System.out.println(">> Cliente Não Possui Reserva! <<");
        } else {
            System.out.println(">> Cliente Possui Reserva <<");
        }
    }

    public static void imprimir_lista(ArrayList<Reserva> lista){
        for (Reserva reserva : lista) {
            System.out.println(reserva);
        }
    }   

    public static String informar_codigo(TpPessoa tp){
        switch (tp){
            case FISICA:
                System.out.print("Informe o CPF: ");
                break;
            case JURIDICA:
                System.out.print("Informe o CNPJ: ");
                break;
        }
        String codigo = getStrInput();
        return codigo;
    }

    public static TpPagamento escolherPagamento(){
        String tp = "";
        while (!tp.equals("a") && !tp.equals("p")) {
            System.out.print("Tipo do Pagamento? [A|P] ");
            tp = getStrInput(); 
            if (!tp.equals("a") && !tp.equals("p")) {
                System.err.println("A: A Vista | P: Parcelado");
            }
        }
        return tp.equals("a") ? TpPagamento.AVISTA : TpPagamento.PARCELADO;
    }

    public static TpPessoa escolherCliente(){
        String tp = "";
        while (!tp.equals("j") && !tp.equals("f")) {
            System.out.print("Tipo do Cliente? [F|J] ");
            tp = getStrInput();   
            if (!tp.equals("j") && !tp.equals("f")) {
                System.err.println("F: Física | J: Jurídica");
            }
        }
        return tp.equals("f") ? TpPessoa.FISICA : TpPessoa.JURIDICA;
    }

    public static void reservaCancelada() {
        System.out.println(">> Reserva Cancelada <<");
    }
}
