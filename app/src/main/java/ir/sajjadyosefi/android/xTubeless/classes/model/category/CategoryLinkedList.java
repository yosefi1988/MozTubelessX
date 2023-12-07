package ir.sajjadyosefi.android.xTubeless.classes.model.category;

public class CategoryLinkedList {
    private CategoryItem head;
    private CategoryItem current;


    public CategoryItem getCurrent() {
        return current;
    }

    public void setCurrent(CategoryItem current) {
        this.current = current;
    }

    public CategoryItem getHead() {
        return head;
    }

    public void setHead(CategoryItem head) {
        this.head = head;
    }

    public String printAllNodes() {
        StringBuilder stringBuilder = new StringBuilder();
        CategoryItem current = head;
        while (current != null)
        {
            stringBuilder.append(current.getTitle() + "\\");
//            current = current.getNextNode();
        }

        return stringBuilder.toString();
    }

    public void AddFirst(CategoryItem data){
        CategoryItem toAdd = data;
//        toAdd.setNextNode(head);
        head = toAdd;
    }


    public void AddLast(CategoryItem data) {
        if (head == null)
        {
            head = new CategoryItem();

            head = data;
//            head.setNextNode(null);
        }
        else
        {
            CategoryItem toAdd = data;
            CategoryItem current = head;
//            while (current.getNextNode() != null)
//            {
//                current = current.getNextNode();
//            }
//
//            current.setNextNode(toAdd);
        }
    }
    public CategoryItem getLast() {
        if (head == null){
            return null;
        }
        else {
            CategoryItem current = head;
//            while (current.getNextNode() != null)
//            {
//                current = current.getNextNode();
//            }

            return current;
        }
    }


}
