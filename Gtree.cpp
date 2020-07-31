#include <iostream>
#include <vector>
#include <stack>

using namespace std;

class Node
{
public:
    int data;
    vector<Node*> childs;

    Node(int data)
    {
        this->data = data;
    }

    Node()
    {
    }
};

Node* createGTTree(vector<int>& arr){
    stack<Node*> st;
    for(int i=0;i<arr.size()-1;i++){

        if(arr[i]!=-1){
            Node* node=new Node(arr[i]);
            st.push(node);
        }else{
            Node* node=st.top(); st.pop();
            st.top()->childs.push_back(node);
        }
    }

    return st.top();
}

void preorder(Node *node){
    cout<<node->data;

    for(Node *child:node->childs){
        preorder(child);
    }
}

int main(){
    int [] arr = {10,20,50,-1,60,-1,-1,30,70,-1,80,-1,90,-1,-1,40,100,-1,110,-1,-1,-1};
    Node *root = createGTTree(arr);
    return 0;
}
