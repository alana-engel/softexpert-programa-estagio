import java.util.Random;
import java.util.Scanner;

class Senha1{
  public static void main(String[] args) {
    boolean acertou;
    NumerosGerados n = new NumerosGerados();
     

    System.out.print("Voce tem 5 tentativas para adivinhar a senha"); 
    n.aleatorio(); // gera os numeros 
      for (int i=0; i<5; i++) {
          System.out.print("\nTentativa "+(i+1)); 
          n.digiteSenha();
          acertou=n.adivinhou();
          if(acertou == true)
          break;
          else if(i==4)
          n.imprime();
      }
  }
}

class NumerosGerados{
  private String recebe, separaNum;
  private int numerosDigitados[] = new int[5];
  private int numeros[] = new int[5]; 
  private boolean valor;
  private Scanner entrada;
  
  void aleatorio(){ // gera os numeros aleatórios 
      Random random = new Random();
      for (int i=0; i<numeros.length; i++) {
          numeros[i] = random.nextInt(10);
      } 
      confereRepetido(); 
  }

  private void confereRepetido(){ // confere se os numeros gerados nao se repetem 
      for (int i = 0; i <= 5; i++) {
          for (int j = i + 1; j < 5; j++) {
              if (numeros[j] == numeros[i]) {
                    aleatorio(); 
              }
          }
      }
  }

  void digiteSenha(){ // recebe a senha digitada
     
      entrada = new Scanner (System.in);
      System.out.println("\nDigite a senha:");
      recebe=entrada.next();
      boolean valida= validaString();
      if( valida== true){
      for (int i=0; i<numerosDigitados.length; i++) {
          separaNum=recebe.substring(i,i+1);
          numerosDigitados[i]=Integer.parseInt(separaNum);
          
      }
      comparaSenha();
      }
   
  }


  private boolean validaString(){
    boolean receb=false;
    boolean v = false;
    int n =recebe.length();
    receb = campoNumerico(recebe);
    if(receb == true && n==5){
      v=true;
    }
    else{
      System.out.println("Digite 5 numeros");
    }
    return(v);
}

  private void comparaSenha(){ // verifica se numeros digitados estão na senha
      valor=true;
      for (int i=0; i<numerosDigitados.length; i++) {
        boolean a=false;
          if(numerosDigitados[i]==numeros[i]){
            System.out.print(numerosDigitados[i]);
          }
          else{
          for (int j = 0; j < 5; j++) {
              if (numeros[j] == numerosDigitados[i]) {
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
          if(numerosDigitados[i]!=numeros[i]){
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
           System.out.print(numeros[i]); 
      }
  }

  private boolean campoNumerico(String campo){           
        return campo.matches("[0-9]+");   
}
}




