/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e2p1_darielsevilla;

import java.util.ArrayList;

public class MaquinaEstado {
    //atributos
    private ArrayList<String> estados = new ArrayList<>();
    private ArrayList<String> estados_aceptacion = new ArrayList<>();
    private ArrayList<String> aristas = new ArrayList<>();
    private String estado_actual;
    
    //constructor
    public MaquinaEstado(String state, String arista){
        estados = splitStr(state, ';');
        aristas = splitStr(arista, ';');
        extractAcceptNodes();
        
        estado_actual = estados.get(0);
        System.out.println(imprimir(estados));
        System.out.println(imprimir(estados_aceptacion));
        System.out.println(imprimir(aristas));
        System.out.println(estado_actual);
    }
    //getters
    public ArrayList<String> getEstados(){
        return estados;
    }
    
    public ArrayList<String> getEstadosAceptacion(){
        return estados_aceptacion;
    }
    
    public ArrayList<String> getAristas(){
        return aristas;
    }
    
    public String getEstadoActual(){
        return estado_actual;
    }
    
    //setters
    public void setEstadoActual(String par){
        estado_actual = par;
    }
    
    public void setAristas(ArrayList<String> par){
        aristas = par;
    }
    
    public void setEstadosAceptacion(ArrayList<String> par){
       estados_aceptacion = par;
    }
    
    public ArrayList<String> setEstados(){
        return estados;
    }
    //metodo de separacion de cadena
    public  ArrayList<String> splitStr(String par, char delim){
        ArrayList<String> lista = new ArrayList<>();
        
        String[] temp = par.split(Character.toString(delim));
        
        for(int i = 0; i < temp.length;i++){
            lista.add(temp[i]);
        }
        
        
        return lista;
    }
    
    
    //impresion de arraylist
    public String imprimir(ArrayList<String> list){
        String temp = "";
        for(int i = 0; i < list.size(); i++){
            temp += list.get(i);
            
            if(i != list.size()-1){
                temp += ", ";
            }else{
                temp += "\n";
            }
           
        }
        return temp;
    }
  
    public void extractAcceptNodes(){
        for(int i = 0; i < estados.size(); i++){
            String state = estados.get(i);
            
            if(state.charAt(0) == '.'){
                state = state.substring(1);
                estados_aceptacion.add(state);
                
                estados.set(i, state);
             
            }
        }
    }
    
    public String computeStr(String cad){
        String resp = "";
        int error = 0;
        for(int i = 0; i < cad.length(); i++){
            //chequear si hay una arista valida
            for(int j = 0; j < estados.size(); j++){
                String temp = String.format("%s,%s,%s", estado_actual, cad.charAt(i), estados.get(j));
                if(aristas.contains(temp)){
                    //actualizar el valor del estado actual
                    String estadoPrevio = estado_actual;
                    String[] arista = temp.split(",");
                    estado_actual = arista[2];
                    resp += String.format("%s:%s -> %s\n", estadoPrevio, cad.charAt(i), estado_actual);
                    break;
                }
                
                if(j == estados.size()-1){
                    error = 1;
                    break;
                }
            }
            
            if(error == 1){
                break;
            }
            
        }
        
        if(error == 1 || !estados_aceptacion.contains(estado_actual)){
            resp += "Rechazada\n";
                
        }else{
            resp += "Aceptada\n";
        }
        
        estado_actual = estados.get(0);
        return resp;
      
    }
    
}
