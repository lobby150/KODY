package harddrive;

public class Catalog {
       private String name;
        private int indexiNode;

    public Catalog(String name, int indexiNode) {
        this.name = name;
        this.indexiNode = indexiNode;
    }

    public String getName() {
        return name;
    }

    public int getIndexiNode() {
        return indexiNode;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setIndexiNode(int indexiNode) {
        this.indexiNode = indexiNode;
    }
        
}
