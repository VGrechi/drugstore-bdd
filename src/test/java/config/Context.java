package config;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Context {

    private Integer clientId;
    private Integer employeeInss;
    private List<Integer> inventory;
    private static Context instance;

    public Context(){
        this.inventory = new ArrayList<>();
    }

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
