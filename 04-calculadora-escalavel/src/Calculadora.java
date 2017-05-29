import java.util.Scanner;

enum Operacao{
	SOMA(1, "Soma"){
		@Override
		public double calcula(double num1, double num2) {
			return num1 + num2;
		}
	},
	SUBTRACAO(2, "Subtração"){
		@Override
		public double calcula(double num1, double num2) {
			return num1 - num2;
		}
	},
	MULTIPLICACAO(3, "Multiplicação"){
		@Override
		public double calcula(double num1, double num2) {
			return num1 * num2;
		}
	},
	DIVISAO(4, "Divisão"){
		@Override
		public double calcula(double num1, double num2) {
			return num1 / num2;
		}
	};
	private int codigo;
	private String descricao;
    private Operacao(int codigo, String descricao){ 
        this.codigo = codigo;
        this.descricao = descricao;
    }
	public abstract double calcula(double num1, double num2);
	
	public static Operacao porCodigo(int codigo)
	{
	    for (Operacao op : values()) {
	        if (op.codigo == codigo)
	            return op;
	    }
	    return null;    
	}
	public int getCodigo() {
		return codigo;
	}
	@Override
	public String toString()
	{
	    return descricao;
	}
}

public class Calculadora {
	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {
		int num1, num2;
		Operacao op= null;
		for (Operacao operacao : Operacao.values())
		    System.out.printf("%d - %s%n", operacao.getCodigo(), operacao);
			System.out.print("Escolha a operação: ");
			op = Operacao.porCodigo(Integer.parseInt(entrada.nextLine()));
			System.out.print("Qual o primeiro numero: ");
			num1 = entrada.nextInt();
			System.out.print("Qual o segundo numero: ");
			num2 = entrada.nextInt();
			System.out.printf("Resultado: " + op.calcula(num1, num2));
		
	}
}
