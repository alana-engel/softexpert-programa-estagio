import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Senha{
  public static void main(String[] args) {
    boolean acertou;
    NumerosDigitados numeroDigitado= new NumerosDigitados();
    
    System.out.print("Voce tem 5 tentativas para adivinhar a senha"); 
    numeroDigitado.numerosGerados.aleatorio(); // gera os numeros 
    for (int i=0; i<5; i++) {
      System.out.print("\nTentativa "+(i+1)); 
      numeroDigitado.digiteSenha();
      acertou=numeroDigitado.adivinhou();
      if(acertou == true)
        break;
      else if(i==4)
        numeroDigitado.imprime();
    }
  }
}

class NumerosGerados{
  ArrayList<Integer> num = new ArrayList<Integer>();

  void aleatorio(){ // gera os numeros aleatórios 
    for (int i = 1; i < 10; i++) { 
      num.add(i);
    }
    Collections.shuffle(num);
  }
}

class NumerosDigitados{
  NumerosGerados numerosGerados = new NumerosGerados();
  private int numerosDigitados[] = new int[5];
  private boolean valor;
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
      comparaSenha();
    }
    else{
      System.out.println("Digite 5 números");
    }
  }

  private void comparaSenha(){ // verifica se numeros digitados estão na senha
    valor=true;
    for (int i=0; i<numerosDigitados.length; i++) {
      boolean a=false;
      if(numerosDigitados[i]==(numerosGerados.num.get(i))){
        System.out.print(numerosDigitados[i]);
      }
      else{
        for (int j = 0; j < 5; j++) {
          if ((numerosGerados.num.get(j)) == numerosDigitados[i]) {
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
      if(numerosDigitados[i]!=(numerosGerados.num.get(i))){
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

  private boolean campoNumerico(String campo){           
    return campo.matches("[0-9]{5}"); 
  }
}
