import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDate;

public class Listagem
{
    private ArrayList<Conta> contas;
    private Scanner scanner;
    private LocalDate data;

    public Listagem(ArrayList<Conta> contas)
    {
        this.contas = contas;
        this.scanner = new Scanner(System.in);
    }


    public void cadastroConta()
    {

        System.out.println("Digite o seu nome desejada: \n");
        String nome = scanner.nextLine();

        long id = Long.parseLong(String.valueOf(contas.size()));

        System.out.println("Digite o valor inicial da conta\n");
        Double saldo = Double.parseDouble(scanner.nextLine());

        System.out.println("Digite a sua chave PIX\n");
        String pix = scanner.nextLine();

        Conta novaConta = new Conta(id, nome, saldo, pix, 0.0);
        contas.add(novaConta);

        contas.toString();


    }

    public boolean estaVazio()
    {
        return contas.isEmpty();
    }

    public int Tamanho()
    {
        return contas.size();
    }

    public void Pix(long id1, String pix)
    {
        int posicaoOrigem = 0;
        int posicaoDestino = 0;
        String chavePixDestino = pix;

        for (int i = 0; i < contas.size(); i++) {
            if (contas.get(i).getId() == id1) {
                posicaoOrigem = i;
            }
            if (Objects.equals(contas.get(i).getPix(), chavePixDestino)) {
                posicaoDestino = i;
            }
        }
        if(posicaoOrigem != 0 && posicaoDestino != 0)
        {
            System.out.println("Digite o valor da transferência: \n");
            double saldo = Double.parseDouble(scanner.nextLine());

            //verifica se o valor a ser transferido é maior que o disponível
            if(contas.get(posicaoOrigem).getCarteira()<saldo)
            {
                System.out.println("Saldo Insuficiente \n");
            }
            else
            {
                //retira do valor "saldo" da conta origem
                contas.get(posicaoOrigem).setCarteira(
                        contas.get(posicaoOrigem).getCarteira() - saldo);

                //Registra operação no histórico da conta origem
                contas.get(posicaoOrigem).
                        registroOperacao(
                                        LocalDate.now() + " - Envio de PIX - " +
                                                          "\n - - Chave Destino: " + chavePixDestino +
                                                          "\n - - Valor da Transferência: " + saldo +
                                                          "\n - - Novo saldo: R$ " + contas.get(posicaoOrigem).getCarteira() + "\n");

                //acrescenta no valor "saldo" da conta destino
                contas.get(posicaoDestino).setCarteira(
                        contas.get(posicaoDestino).getCarteira() + saldo);

                //Registra operação no histórico da conta destino
                contas.get(posicaoDestino).
                        registroOperacao(
                                        LocalDate.now() + " - Recebimento de PIX - " +
                                                          "\n - - Chave Origem: " + contas.get(posicaoOrigem).getPix() +
                                                          "\n - - Valor da Transferência: " + saldo +
                                                          "\n - - Novo saldo: R$ " + contas.get(posicaoDestino).getCarteira() + "\n");
            }

        }
        else if (posicaoOrigem == 0)
        {
            System.out.println("Erro: Conta origem não encontrada.\n");
        }
        else if(posicaoDestino == 0)
        {
            System.out.println("Erro: Conta destino não encontrada.\n");
        }


    }

    public int VerificarContaPorId(long id)
    {
        for (int i = 0; i < contas.size(); i++)
        {
            if (contas.get(i).getId() == id)
            {
                System.out.println(contas.get(i).toString());
                System.out.println("\nOs dados estão corretos? \n" +
                                   "1 - Sim\n" +
                                   "2 - Não\n");

                int af = Integer.parseInt(scanner.nextLine());

                if (af == 1) {
                    return i;
                } else if (af == 2) {
                    System.out.println("Continuar Procurando");
                } else {
                    System.out.println("Opção Inválida");
                    return 0;
                }
            }
        }

        System.out.println("Conta não encontrada");
        return 0;
    }


}
