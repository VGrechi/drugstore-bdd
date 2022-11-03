package config;

import lombok.Data;

@Data
public class Context {

    private static Context instance;

    public static Context getInstance(){
        if(instance == null){
            instance = new Context();
        }
        return instance;
    }

    public void reset(){
        this.instance = null;
    }



}
