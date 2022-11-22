package model.types;

import model.values.BoolValue;


public class BoolType implements Type{

    public boolean equals(Object another){
        return another instanceof BoolType;
    }

    public BoolValue getDefaultValue() {
        return new BoolValue(false);
    }

    @Override
    public String toString(){
        return "bool";
    }
}
