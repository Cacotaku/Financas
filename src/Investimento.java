import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Investimento
{

    private LocalDate data;
    private Scanner scanner;
    private String nomeInvestimento;
    private double valorInvestimento;

    Investimento(LocalDate data, String nomeInvestimento, double valorInvestimento)
    {
        this.nomeInvestimento = nomeInvestimento;
        this.data = data;
        this.valorInvestimento = valorInvestimento;
        this.scanner = new Scanner(System.in);

    }


    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getNomeInvestimento() {
        return nomeInvestimento;
    }

    public void setNomeInvestimento(String nomeInvestimento) {
        this.nomeInvestimento = nomeInvestimento;
    }

    public double getValorInvestimento() {
        return valorInvestimento;
    }

    public void setValorInvestimento(double valorInvestimento) {
        this.valorInvestimento = valorInvestimento;
    }



    @Override
    public String toString() {
        return "Investimento{" +
                "data=" + data +
                ", scanner=" + scanner +
                ", nomeInvestimento='" + nomeInvestimento + '\'' +
                ", valorInvestimento=" + valorInvestimento +
                '}';
    }




}
