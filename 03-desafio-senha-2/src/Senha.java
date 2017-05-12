import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Senha{
  public static void main(String[] args) {
    boolean acertou;
    VerificaNumeros numeros= new VerificaNumeros();
    System.out.print("Voce tem 5 tentativas para adivinhar a senha"); 
    numeros.numerosGerados.aleatorio(); // gera os numeros 
    for (int i=0; i<5; i++) {
      System.out.print("\nTentativa "+(i+1)); 
      numeros.numDigitados.digiteSenha();
      numeros.comparaSenha();
      acertou=numeros.adivinhou();
      if(acertou == true)
        break;
      else if(i==4)
        numeros.imprime();
    }
  }
}

class GeraNumeros{
  ArrayList<Integer> num = new ArrayList<Integer>();

  void aleatorio(){ // gera os numeros aleatórios 
    for (int i = 1; i < 10; i++) { 
      num.add(i);
    }
    Collections.shuffle(num);
  }
}

class RecebeNumerosDigitados{
  int numerosDigitados[] = new int[5];
  private Scanner entrada;
  void digiteSenha(){ // recebe a senha digitada
    String recebe, separaNum;
    entrada = new Scanner (System.in);
    System.out.println("\nDigite a senha:");
    recebe=entrada.next();
    boolean valida = campoNumerico(recebe);
    if( valida== true){
      for (int i=0; i<numerosDigitados.length; i++) {
        separaNum=recebe.substring(i,i+1);
        numerosDigitados[i]=Integer.parseInt(separaNum);
      }
    }
    else{
      System.out.println("Digite 5 números");
    }
  }
  private boolean campoNumerico(String campo){           
    return campo.matches("[0-9]{5}"); 
  }
}

class VerificaNumeros{
  private boolean valor;
  GeraNumeros numerosGerados=new GeraNumeros();
  RecebeNumerosDigitados numDigitados = new RecebeNumerosDigitados();
  void comparaSenha(){ // verifica se numeros digitados estão na senha
    valor=true;
    for (int i=0; i<numDigitados.numerosDigitados.length; i++) {
      boolean a=false;
      if(numDigitados.numerosDigitados[i]==(numerosGerados.num.get(i))){
        System.out.print(numDigitados.numerosDigitados[i]);
      }
      else{
        for (int j = 0; j < 5; j++) {
          if ((numerosGerados.num.get(j)) == numDigitados.numerosDigitados[i]) {
            a=true;
          }
        }
        if(a==true){
          System.out.print("#");
        }
        else{
          System.out.print("*");
        } 
      }
      if(numDigitados.numerosDigitados[i]!=(numerosGerados.num.get(i))){
        valor=false;
      }
    } 
  }

  boolean adivinhou(){ 
    if(valor == true){
      System.out.print("  Voce adivinhou a senha!"); 
    } 
    return valor;
  }

  void imprime(){ // Imprime na tela os numeros gerados
    System.out.print("\ngame over\nSenha gerada: "); 
    for (int i=0; i<5; i++) {
      System.out.print((numerosGerados.num.get(i))); 
    }
  }
}