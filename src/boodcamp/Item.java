/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boodcamp;


public class Item {
    private String title, actualday, text;
    private int temp;    
    private Forecast[] fore; 

    public Item(String title, String actualday, String text, int temp, int n) {
        this.title = title;
        this.actualday = actualday;
        this.text = text;
        this.temp = temp;
        fore = new Forecast[n];
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActualday() {
        return actualday;
    }

    public void setActualday(String actualday) {
        this.actualday = actualday;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public Forecast[] getFore() {
        return fore;
    }

    public void setFore(Forecast[] fore) {
        this.fore = fore;
    }
      
    
    public void addDay(int cod, String date, String day, String text, float high, float low){
        
        Forecast c = new Forecast(date, day, text, high, low);        
        
        for(int i = 0; i < fore.length; i++){
            if(fore[i]==null){
                fore[i]=c;
              return;  
            }
        }
        
        
    }
    
   
    public Forecast showForecast(int n){
        return fore[n];     
        
        
        
    }
    
    
    
    
}
