import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
public class Gtree{
    public static void main (String[] args){
        solve();
    }

    public static class Node{
        int data = 0;
        ArrayList<Node> childs = new ArrayList<>();
        
        Node(int data){
            this.data = data;
        }

        Node(){

        }
    }


    public static Node createGtree(int[] arr){
        Stack<Node> st = new Stack<>();

        for(int i=0;i<arr.length-1;i++){
            if(arr[i]!=-1){
                Node node = new Node(arr[i]);
                st.push(node);
            }
            else{
                Node node = st.pop();
                st.peek().childs.add(node);
            }
        }
        return st.pop();
    }

    public static void preorder(Node node){
        System.out.print(node.data + " ");
        for(Node child : node.childs){
            preorder(child);
        }
    }

    public static void display(Node node){
        String str = " ";
        str += node.data + "-> ";
    
        for(Node child:node.childs){
            str+=child.data+" ";
        }
        
        System.out.println(str);
        
        for(Node child:node.childs){
            display(child);
        }
    
    }

    public static int height(Node node){
        int h = 0;
        
        for(Node child:node.childs){
            h = Math.max(h,height(child));
        }

        return h+1;
    }

    public static int size(Node node){
        int s = 0;
        for(Node child:node.childs){
            s+=size(child);
        }
        return s+1;
    }

    public static boolean find(Node node, int data){
        if(node.data==data) return true;
        boolean res = false;
        for(Node child:node.childs){
             
            res = res||find(child,data);

            if(res==true) break;
            
        }
        return res;
    }

    public static boolean rootToNodePath(Node node,int data,ArrayList<Node>path){
        if(node.data == data){
            path.add(node);
            return true;
        }

        boolean res = false;
        path.add(node);
        for(Node child:node.childs){
            res = res||rootToNodePath(child, data, path);
        }

        if(res == false) path.remove(path.size()-1);
        return res;
    }

    public static void levelorder(Node node){
        LinkedList<Node>que=new LinkedList<>(); //add Last remove first
        que.addLast(node);
        while(que.size()!=0){
            int size = que.size();
            while(size > 0){
                Node rnode = que.removeFirst();
                System.out.print(rnode.data+" ");

                for(Node child : rnode.childs){
                    que.addLast(child);
                }
                size--;
            }
            System.out.println();
        }

    }

    public static boolean isMirror(Node root1,Node root2){
        if(root1.childs.size()!=root2.childs.size() || root1.data !=root2.data) return false;
        for(int i=0,j=root2.childs.size()-1; j>=0;i++,j--){
            Node first = root1.childs.get(i);
            Node last = root2.childs.get(j);

            if(isMirror(first,last)==false) return false;
        }

        return true;
    }

    public static Node linearize(Node node){
        if(node.childs.size()==0) return node;
    
        int n=node.childs.size();
        Node lastTail=linearize(node.childs.get(n-1));
        for(int i = n-2;i>=0;i--){
            Node secondLastTail=linearize(node.childs.get(i));
            
            secondLastTail.childs.add(node.childs.get(i+1));  // connection between two linearize structure.
            
            node.childs.remove(node.childs.size()-1); //remove last node.
        }
    
        return lastTail;
    }

    public static void flattern(Node node){
        ArrayList<Node> nchilds=new ArrayList<>();
    
        for(Node child: node.childs){
            flattern(child);

            nchilds.add(child);
            for(Node ch: child.childs){
                nchilds.add(ch);
            }
            child.childs.clear();
        }

        node.childs.clear();
        node.childs=nchilds;
    }

    public static void solve(){
        int [] arr = {10,20,50,-1,60,-1,-1,30,70,-1,80,-1,90,-1,-1,40,100,-1,110,-1,-1,-1};
        Node root = createGtree(arr);
        // preorder(root);
        // display(root); 
        // System.out.println(height(root)); 
        // System.out.println(size(root));
        // System.out.println(find(root,60));
            
        //     ArrayList<Node>path = new ArrayList<>();
        //         rootToNodePath(root,80,path);
        //         for(int i=0;i<path.size();i++){
        //             System.out.print(path.get(i).data + " ");
        //         }

        // levelorder(root);
        
            // int [] arr = {10,20,50,-1,60,-1,70,-1,-1,30,80,-1,80,-1,-1,20,70,-1,60,-1,50,-1,-1,-1};
        //     System.out.println(isMirror(root, root)); 

        // linearize(root);
        // display(root);

        // flattern(root);
        // display(root);
    }
}