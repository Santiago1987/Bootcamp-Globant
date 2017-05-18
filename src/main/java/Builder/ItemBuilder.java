package Builder;


import Model.Item;

public class ItemBuilder {
    private String title = "", actualday= "", text= "";
    private int temp = 0;

    public ItemBuilder(String title, String actualday, String text, int temp) {
        this.title = title;
        this.actualday = actualday;
        this.text = text;
        this.temp = temp;
    }

    public ItemBuilder title(String title){
        this.title = title;
        return this;
    }

    public ItemBuilder actualday(String actualday){
        this.actualday = actualday;
        return this;
    }

    public ItemBuilder text(String text){
        this.text = text;
        return this;
    }


    public Item build(){
        return new Item(this);
    }

    public ItemBuilder temp(int temp){
        this.temp = temp;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getActualday() {
        return actualday;
    }

    public String getText() {
        return text;
    }

    public int getTemp() {
        return temp;
    }


}
