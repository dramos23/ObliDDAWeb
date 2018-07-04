/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author alumnoFI
 */
public class ComponentesHtml {
    
     public static String lista(boolean multiple,String id,ArrayList opciones){
        String lista = "<select " + (multiple?" multiple ":"") +  " id='" + id+ "'>";
        
        for(Object obj:opciones){
            lista+= "<option>" + obj + "</option>";
        }
        lista+="</select>";
        return lista;
    }
     
    public static String tblImg(String id, ArrayList opciones){
        String tblImg = "<table><tr>";
        for(Object obj:opciones){
            tblImg+= "<td><img src=\"" + obj + " ></td>";
        }
                
        tblImg = "<tr><table>";        
        return tblImg;
    }
    
}
