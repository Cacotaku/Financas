import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    private Scanner scanner;

    public Conta BuscarContaPorId(ArrayList<Conta> contas)
    {
        scanner = new Scanner(System.in);
        System.out.println("Digite o Id do usuário: \n");
        long idProcurado = Long.parseLong(scanner.nextLine());

        for(Conta conta : contas)
        {
            if(conta.getId() == idProcurado)
            {
                return conta;
            }
        }

        System.out.println("Conta com ID " + idProcurado + " não encontrada.");
        return null;

    }

    public static void Menu()
    {

        String menu = "\n Sistema Financeiro\n\n" +
                      "1 - Cadastrar Conta\n" +
                      "2 - Depósito\n" +
                      "3 - Saque\n" +
                      "4 - PIX\n" +
                      "5 - Investir\n" +
                      "6 - Histórico de Investimentos\n" +
                      "7 - Exibir Saldo na Tela\n" +
                      "9 - Historico de Transações\n" +
                      "0 - Sair\n";

        System.out.println(menu);
    }



    public static void main(String[] args) {
        // Create a list to store accounts
        ArrayList<Conta> contas = new ArrayList<>();
        Listagem gerenciador = new Listagem(contas);


        Scanner scanner = new Scanner(System.in);

        boolean teste;

        int opc = -1;
        int posicao;

        double valor;

        long idProcurar;
        long idOrigem;

        String pixDestino;

        while(true)
        {
            Menu();
            opc = Integer.parseInt(scanner.nextLine());

            switch (opc)
            {
                case 1: // Cadastro de uma nova conta
                    System.out.println("Cadastro de conta: \n");
                    if(gerenciador.estaVazio() == true)
                    {
                        contas.add(new Conta(0L, "Conta Inválida", 0.0, "null", 0.0));
                    }
                    gerenciador.cadastroConta();

                    break;

                case 2: //Depósito

                    System.out.println("Depósito: \n");
                    System.out.println("Digite a conta destino: \n");

                    idProcurar = Long.parseLong(scanner.nextLine());
                    posicao = gerenciador.VerificarContaPorId(idProcurar);

                    if(posicao == 0)
                    {
                        break;
                    }

                    for (Conta conta : contas)
                    {
                       if(conta.getId() == idProcurar)
                       {
                           System.out.println("Digite o valor depositado: \n");
                           valor = Double.parseDouble(scanner.nextLine());
                           conta.Deposito(valor);

                       }
                    }

                    break;

                case 3: //Saque

                    System.out.println("Saque: \n");
                    System.out.println("Digite a conta desejada: \n");
                    teste = true;

                    idProcurar = Long.parseLong(scanner.nextLine());
                    posicao = gerenciador.VerificarContaPorId(idProcurar);

                    if(posicao == 0)
                    {
                        break;
                    }

                    for (Conta conta : contas)
                    {
                       if(conta.getId() == idProcurar)
                       {
                           System.out.println("Digite a ser sacado: \n");
                           valor = Double.parseDouble(scanner.nextLine());
                           teste = conta.Saque(valor);

                       }
                    }
                    if(teste == false)
                    {
                        System.out.println("Operação não realizada.\n");
                    }
                    break;

                case 4: //PIX

                    System.out.println("Transferência: \n");
                    System.out.println("Digite a sua Conta: \n");
                    idOrigem = Long.parseLong(scanner.nextLine());

                    System.out.println("Digite a chave PIX da conta destino: \n");
                    pixDestino = scanner.nextLine();

                    gerenciador.Pix(idOrigem, pixDestino);

                    break;

                case 5: //Investimentos

                    System.out.println("Transferência: \n");
                    System.out.println("Digite a sua Conta: \n");

                    idProcurar = Long.parseLong(scanner.nextLine());
                    posicao = gerenciador.VerificarContaPorId(idProcurar);


                    if(posicao == 0)
                    {
                        break;
                    }

                    for (Conta conta : contas)
                    {
                       if(conta.getId() == idProcurar)
                       {
                           conta.cadastroDeInvestimento();

                       }
                    }

                    break;

                case 6: //Verificar Investimentos

                    System.out.println("Histórico de Investimentos: \n");
                    System.out.println("Digite a sua Conta: \n");

                    idProcurar = Long.parseLong(scanner.nextLine());
                    posicao = gerenciador.VerificarContaPorId(idProcurar);

                    if(posicao == 0)
                    {
                        break;
                    }

                    for (Conta conta : contas)
                    {
                        if (conta.getId() == idProcurar)
                        {
                            conta.mostrarInvestimentos();
                        }
                    }

                    break;

                case 7: //Mostrar saldo na tela

                    System.out.println("Saldo: \n");
                    System.out.println("Digite a sua Conta: \n");

                    idProcurar = Long.parseLong(scanner.nextLine());
                    posicao = gerenciador.VerificarContaPorId(idProcurar);

                    if(posicao == 0)
                    {
                        break;
                    }

                    for (Conta conta : contas)
                    {
                        if (conta.getId() == idProcurar)
                        {
                            conta.Saldo();
                        }
                    }

                    break;


                case 9:
                    System.out.println("Histórico da transações: \n");
                    System.out.println("Digite a sua Conta: \n");

                    idProcurar = Long.parseLong(scanner.nextLine());
                    posicao = gerenciador.VerificarContaPorId(idProcurar);

                    if(posicao == 0)
                    {
                        break;
                    }

                    for (Conta conta : contas)
                    {
                        if (conta.getId() == idProcurar)
                        {
                            conta.mostrarHistorico();
                        }
                    }
                    break;

                case 0:

                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção Inválida.\n");
                    break;
            }
        }
    }
}