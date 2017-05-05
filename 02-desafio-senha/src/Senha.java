import java.util.*;

class Senha{
  public static void main(String[] args) {
    boolean adiv;
    NumerosGerados n = new NumerosGerados();

    System.out.print("Voce tem 5 tentativas para adivinhar a senha"); 
    n.aleatorio(); // gera os numeros 
      for (int i=0; i<5; i++) {
          System.out.print("\nTentativa "+(i+1)); 
          n.digiteSenha();
          n.comparaSenha();
          adiv=n.adivinhou();
          if(adiv == true)
           break;
          else if(i==4)
          n.imprime();
      }
  }
}

class NumerosGerados{

  int numerosDigitados[] = new int[5];
  int numeros[] = new int[5]; 
  boolean valor=true;
  


  void aleatorio(){ // gera os numeros aleatórios 
      Random random = new Random();
      for (int i=0; i<numeros.length; i++) {
          numeros[i] = random.nextInt(10);
      } 
      confereRepetido(); 
  }

  void confereRepetido(){ // confere se os numeros gerados nao se repetem 
      for (int i = 0; i <= 5; i++) {
          for (int j = i + 1; j < 5; j++) {
              if (numeros[j] == numeros[i]) {
                    aleatorio(); 
              }
          }
      }
  }

  void digiteSenha(){ // recebe a senha digitada
        
      String recebe, separaNum;
      Scanner entrada = new Scanner (System.in);
      System.out.println("\nDigite a senha:");
      recebe=entrada.next();
      int n =recebe.length();
      if(n==5){
      for (int i=0; i<numerosDigitados.length; i++) {
          separaNum=recebe.substring(i,i+1);
          numerosDigitados[i]=Integer.parseInt(separaNum);
      }
      }
      else{
         System.out.println("Digite 5 numeros");
     }
  }

  void comparaSenha(){ // verifica se numeros digitados estão na senha
      valor=true;
      for (int i=0; i<numerosDigitados.length; i++) {
          if(numerosDigitados[i]==numeros[i]){
            System.out.print(numerosDigitados[i]);
          }
          else{
            System.out.print("*");
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
}



