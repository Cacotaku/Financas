import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Conta
{

     private long id;
     private String nome;
     private double carteira;


    private double carteiraInvestimento;
     private Scanner scanner;
     private String pix;
     private ArrayList<String> historico;
     private ArrayList<Investimento> investimentos;


    Conta(long id, String nome, double carteira, String pix, double carteiraInvestimento)
    {
        this.id = id;
        this.nome = nome;
        this.carteira = carteira;
        this.pix = pix;
        this.historico = new ArrayList<>();
        this.investimentos = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.carteiraInvestimento = carteiraInvestimento;


    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPix() {
        return pix;
    }

    public void setPix(String pix) {
        this.nome = nome;
    }


    public double getCarteiraInvestimento() {
        return carteiraInvestimento;
    }

    public void setCarteiraInvestimento(double carteiraInvestimento) {
        this.carteiraInvestimento = carteiraInvestimento;
    }

    public double getCarteira() {
        return carteira;
    }

    public void setCarteira(double carteira) {
        this.carteira = carteira;
    }

    public void Deposito(double saldo)
    {
        if(saldo > 0)
        {
            System.out.println("Novo Saldo: \n");

            setCarteira(this.carteira + saldo);

            System.out.println("R$: " + getCarteira() + "\n");

            //Acrescenta o depósito no histórico
            this.historico.add(LocalDate.now() + " - Deposito Realizado - " +
                                                          "\n - - Valor da Transferência: " + saldo +
                                                          "\n - - Novo saldo: R$ " + this.carteira + "\n");
        }
        else
        {
            System.out.println("Erro!\n Valor de depósito menor que 0.\n");
        }
    }

    public boolean Saque(double saldo)
    {
        if(this.carteira >= saldo)
        {
            System.out.println("Novo Saldo: \n");
            System.out.println("R$: " + (carteira - saldo) + "\n");

            this.carteira = carteira - saldo;

            //Acrescenta o saque no histórico
            this.historico.add(LocalDate.now() + " - Saque Realizado - " +
                                                 "\n - - Valor da Transferência: " + saldo +
                                                 "\n - - Novo saldo: R$ " + this.carteira + "\n");

            return true;
        }

        System.out.println("Saldo Insuficiente\n");
        return false;

    }


    public void Saldo()
    {
        System.out.println("Conta: " + this.id + "\n" +
                            "Nome: " + this.nome + "\n" +
                            "Saldo: R$ " + carteira + "\n" +
                            "Valor Investido: R$ " + carteiraInvestimento + "\n"
        );
    }

    @Override
    public String toString() {
        return "Conta: " + id + "\n" +
               "Nome: " + nome + "\n" +
               "Saldo: R$ " + carteira + "\n";
    }

    public void mostrarHistorico()
    {
        System.out.println("Histórico da conta " + id + ":");

        if (historico.isEmpty())
        {
            System.out.println("Nenhuma operação registrada.");
        }
        else
        {
            for (String operacao : historico)
            {
                System.out.println("- " + operacao);
            }
        }
    }

    public void mostrarInvestimentos()
    {
        System.out.println("Histórico de investimentos\n" +
                "Conta: " + id + ":");

        if (historico.isEmpty())
        {
            System.out.println("Nenhuma operação registrada.");
        }
        else
        {
            for (Investimento investimento : investimentos)
            {
                System.out.println("- Data da operação:  " + investimento.getData() + "\n" +
                                   "- - Nome do Investimento: " + investimento.getNomeInvestimento() + "\n" +
                                   "- - Valor do Investimento: " + investimento.getValorInvestimento() + "\n");
            }
        }
    }

    public void cadastroDeInvestimento()
    {
        System.out.println("Investimentos\n");

        System.out.println("Digite o nome do Investimento desejado: \n");

        String nome = scanner.nextLine();

        System.out.println("Digite o valor que deseja investir: \n");

        double valorInvestido = Double.parseDouble(scanner.nextLine());

        if(valorInvestido > this.carteira)
        {
            System.out.println("Saldo Insuficiente.\n");
        }
        else
        {
            LocalDate data = LocalDate.now();

            this.carteira = this.carteira - valorInvestido;
            this.carteiraInvestimento = this.carteiraInvestimento + valorInvestido;

            Investimento novoInvestimento = new Investimento(data, nome, valorInvestido);

            System.out.println("Novo Saldo: R$ " + this.carteira);
            investimentos.add(novoInvestimento);

            //Registrar investimento no histórico
            this.historico.add(LocalDate.now() + " - Investimento Realizado - " +
                                                     "\n - - Valor da Transferência: " + valorInvestido +
                                                     "\n - - Novo saldo: R$ " + this.carteira + "\n" +
                                                     "\n - - Valor total de investimento: R$ " + this.carteiraInvestimento + "\n");


        }

    }


    public void registroOperacao(String operacao)
    {
        historico.add(operacao);

    }

}
