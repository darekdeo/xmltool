package models

class Item {

    String id;
    String text;

    @Override
    public boolean equals(Object o){
        if (o == null)
            false
        if (o.is(this))
            true
        if (o instanceof Item) {
            Item toCompare = (Item) o;
            return id == toCompare.id;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }
}
