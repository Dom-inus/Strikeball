package strikeball;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Dominus
 */
public class Strikeball {
    private static int[] numb = {10, 10, 10, 10};
    private int[][] numIns;
    private int tentativi;
    
    public Strikeball(){
        int temp;
        this.tentativi=intin("\nQuanti tentativi vuoi avere?  ");
        Random rand = new Random();
        
        
        //Riempe la sequenza da indovinare 'numb' con 4 valori diversi
        for(int i=0; i<4; i++){
            temp = rand.nextInt((9 - 0) + 1) + 0;
            if(temp!=numb[0] && temp!=numb[1] && temp!=numb[2] && temp!=numb[3])
                numb[i] = temp;
        }
        System.out.println(Arrays.toString(numb));
        numIns = new int[this.tentativi]/*rows    columns*/[4];
        
        
        //Riempe la tabella di NUMeri INSeriti con valori flag
        for(int i=0;i<tentativi;i++){
            for(int y=0; y<4; y++){
                numIns[y][i]=10;
            }
        }
        stampaTabella("void");
    }

    //L'utente fa un tentativo, questo viene controllato e vengono dati suggerimenti, in caso di corrispondenza totale l'utente vince
    public void tentativo(){
        String tent;
        do{
            System.out.println("Inserire il numero di 4 cifre (es. '1234')");
            tent=strin();
        }while(!tent.matches("[0-9]{4}"));
        
        
        //R
        String check = checkNumb(convertitore(tent));
        String findStr = "Rosso";
        int lastIndex = 0;
        int count = 0;
            //Conta quante volte la parola ROSSO è presente, se 4 si conclude
        while(lastIndex != -1){

            lastIndex = check.indexOf(findStr,lastIndex);

            if(lastIndex != -1){
                count ++;
                lastIndex += findStr.length();
            }
        }
        
        if(count==4){
            System.out.println("HAI VINTO!");
        }
    }
    
    //Stampa la tabella ad ogni input e mostra tutti i tentativi
    public void stampaTabella(String check){
        System.out.println("┌─┬─┬─┬─┐");
        
        for(int i=0; i<this.tentativi-1;i++){
            //Se il valore della tabella ha il flag stampa le celle vuote sennò stampa tutti i valori precedentemente inseriti
            if(this.numIns[i][0]==10){
                System.out.println("│ │ │ │ │\n├─┼─┼─┼─┤");
            } else {
                System.out.println("│"+numIns[i][0]+"│"+numIns[i][1]+"│"+numIns[i][2]+"│"+numIns[i][3]+"│"+check+"\n├─┼─┼─┼─┤");
            }
        }
        System.out.println("│ │ │ │ │\n└─┴─┴─┴─┘");
    }
    
    //Controlla l'input dell'utente e ritorna i suggerimenti
    private String checkNumb(int[] tent){
        String[] result;
        result = new String[4];
        for(int i=0; i<4; i++){
            for(int y=0; y<4; y++){
                        //Se un numero inserito dall'utente si trova nella sequenza corretta e nella posizione corretta riporta il flag ROSSO
                if(numb[i]==tent[y] && i==y){
                    System.out.println("Rosso");
                    result[i]="Rosso";
                    
                        //Se un numero inserito dall'utente si trova nella sequenza corretta riporta il flag BIANCO
                } else if(numb[i]==tent[y]){
                    System.out.println("Bianco");
                    result[i]="Bianco";
                }
            }
        }
        return "\n[ "+result[0]+" | "+result[1]+" | "+result[2]+" | "+result[3]+"]";
    }
    
    
    //Input numero intero
    private Integer intin(String richiesta) {
        System.out.print(richiesta);
        Integer num = 0;
        String stringa;
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        try {
            stringa = buffer.readLine();
            num = Integer.valueOf(stringa);
        }
        catch (Exception e) {
            System.out.println("Errore: " + e + " nella lettura da tastiera");
            System.exit(-1);
        }
        return (num);
    }        
    //Input stringa intero
    private String strin() {
        String stringa = null;
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        try {
            stringa = buffer.readLine();
        }
        catch (Exception e) {
            System.out.println("Errore: " + e + " nella lettura da tastiera");
            System.exit(-1);
        }
        return stringa;
    }
    //String -> int[]
    private int[] convertitore(String tentSTR){
        int[] tentINT = new int[4];
        tentINT[0]= Integer.valueOf(tentSTR.substring(0, 1));
        tentINT[1]= Integer.valueOf(tentSTR.substring(1, 2));
        tentINT[2]= Integer.valueOf(tentSTR.substring(2, 3));
        tentINT[3]= Integer.valueOf(tentSTR.substring(3, 4));
        return tentINT;
    }
}