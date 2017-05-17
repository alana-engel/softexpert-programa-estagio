import java.util.Scanner;

enum Operacao1{
	SOMA(1){
		@Override
		public double calcula(double num1, double num2) {
			return num1 + num2;
		}
	},
	SUBTRACAO(2){
		@Override
		public double calcula(double num1, double num2) {
			return num1 - num2;
		}
	},
	MULTIPLICACAO(3){
		@Override
		public double calcula(double num1, double num2) {
			return num1 * num2;
		}
	},
	DIVISAO(4){
		@Override
		public double calcula(double num1, double num2) {
			return num1 / num2;
		}
	};
	private int codigo;
    private Operacao1(int codigo){ 
        this.codigo = codigo;
    }
	public abstract double calcula(double num1, double num2);
	public static Operacao1 porCodigo(int codigo)
	{
	    for (Operacao1 op : values()) {
	        if (op.codigo == codigo)
	            return op;
	    }
	    return null;    
	}
	public int getCodigo() {
		return codigo;
	}
}

public class Calculadora {
	private static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {
		int num1, num2;
		Operacao1 op= null;
			System.out.println("Opcoes de Operacao");
			System.out.println("1. Soma");
			System.out.println("2. Subtracao");
			System.out.println("3. Multiplicacao");
			System.out.println("4. Divisao");
			System.out.print("Escolha a operação: ");
			op = Operacao1.porCodigo(Integer.parseInt(entrada.nextLine()));
			System.out.print("Qual o primeiro numero: ");
			num1 = entrada.nextInt();
			System.out.print("Qual o segundo numero: ");
			num2 = entrada.nextInt();
			System.out.printf("Resultado: " + op.calcula(num1, num2));
		
	}
}
