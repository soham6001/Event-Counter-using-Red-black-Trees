

public class RedBlackTree {
	//**** The Sentinel Nodes by default are black in colour ****
	 private Node sentinel=new Node(-2,-2);
	    private Node root=sentinel;
	
	   // **** The current function and its helper function is responcible for recursively computing **** 
	   // ****      around the middle element from a sorted array to create the Red Black Tree       ****

	   public void initializeRBT(int arr[][],int start,int end,int n){
		   //height is computed as log base 2 of the total number of nodes
	        root=initializeRBThelper(arr,start,end,0,(int) Math.ceil((Math.log(n))/Math.log(2)));
	        root.parent=sentinel;
	        root.color='b';
	    }
		
				
		//**** the last non-sentinel level is coloured red to maintain the height ****
	    
		public Node initializeRBThelper(int arr[][],int start,int end,int level,int height){
	        if(start>end)
	            return sentinel;
	        int mid = start + (end - start) / 2;
	        Node node=new Node(arr[mid][0],arr[mid][1]);
	        if(level==height-1)
	            node.color='r';
            node.left=initializeRBThelper(arr, start, mid-1,level+1,height);
	        node.left.parent=node;
	        node.right=initializeRBThelper(arr, mid+1, end,level+1,height);
	        node.right.parent=node;
	        return node;
	    }
	 
	 //*******************************************************************************************************************************************************
	 
	 //**** inserts the ID count pair at appropriate position comparing existing RBnodes  ****
	 
	 //*******************************************************************************************************************************************************

	
	 public void insert(int ID, int count) {
	        Node node = new Node(ID,count);
	        node.color='r';
	        node.left=sentinel;
	        node.right=sentinel;
	        if (root == sentinel) {
	            root = node;
	            root.color='b';
	            root.parent=sentinel;
	            return;
	        }
	        Node cur = root;
	        Node pre = root;
	        while (cur != sentinel) {
	            pre = cur;
	            if (node.ID < cur.ID) {
	                cur = cur.left;
	            } else {
	                cur = cur.right;
	            }
	        }
	        node.parent=pre;
	        if (node.ID < pre.ID) {
	            pre.left=node;
	        } else {
	            pre.right=node;
	        }
	        insertFixer(node);
	    }
		
		//**** Performs colour swaps and rotations in the Red Black tree to maintain the RB tree rules ****
	 
	    public void insertFixer(Node z){
	        while(z.parent.color=='r'){
	            if(z.parent==z.parent.parent.left){
	                Node y=z.parent.parent.right;
	                if(y.color=='r'){
	                    z.parent.color='b';
	                    y.color='b';
	                    z.parent.parent.color='r';
	                    z=z.parent.parent;
	                }
	                else{
	                    if(z==z.parent.right){
	                        z=z.parent;
	                        leftRotate(z);
	                    }
	                    z.parent.color='b';
	                    z.parent.parent.color='r';
	                    rightRotate(z.parent.parent);
	                }
	            }
	            else{
	                Node y=z.parent.parent.left;
	                if(y.color=='r'){
	                    z.parent.color='b';
	                    y.color='b';
	                    z.parent.parent.color='r';
	                    z=z.parent.parent;
	                }
	                else{
	                    if(z==z.parent.left){
	                        z=z.parent;
	                        rightRotate(z);
	                    }
	                    z.parent.color='b';
	                    z.parent.parent.color='r';
	                    leftRotate(z.parent.parent);
	                }
	            }
	        }
	        root.color='b';
	    }
	     
		//*******************************************************************************************************************************************************
		
	    //**** Does not ensure the balance like Binary Search tree and just deletes the given node ****
		
		//*******************************************************************************************************************************************************
		
		
	    public void delete(Node z){
	        Node y=z;
	        Node x;
	        char origColor = y.color;
	        if(z.left==sentinel){
	            x=z.right;
	            //node z parent becomes node z.right parent and z parent ends up having z.right as its appropriate child
	            transplant(z,z.right);
	        }
	        else if(z.right==sentinel){
	            x=z.left;
	            transplant(z,z.left);
	        }
	        else{
	            y=z.right;
	            while(y.left!=sentinel)
	                y=y.left;
	            origColor=y.color;
	            x=y.right;
	            if(y.parent==z)
	                x.parent=y;
	            else{
	                transplant(y,y.right);
	                y.right=z.right;
	                y.right.parent=y;
	            }
	            transplant(z,y);
	            y.left=z.left;
	            y.left.parent=y;
	            y.color=z.color;
	        }
	        if(origColor=='b')
	        	//calls fixer to correct rbtree properties on x
	            deleteFixer(x);
	    }
	    
	    //**** Performs the colour swaps and tree rotations to satisfy the red black tree rules ****
		
	    public void deleteFixer(Node x){
	        while(x!=root&&x.color=='b'){
	            if(x==x.parent.left){
	                Node w=x.parent.right;
	                if(w.color=='r'){
	                    w.color='b';
	                    x.parent.color='r';
	                    leftRotate(x.parent);
	                    w=x.parent.right;
	                }
	                if(w.left.color=='b'&&w.right.color=='b'){
	                    w.color='r';
	                    x=x.parent;
	                }
	                else{
	                    if(w.right.color=='b'){
	                        w.left.color='b';
	                        w.color='r';
	                        rightRotate(w);
	                        w=x.parent.right;
	                    }
	                    w.color=x.parent.color;
	                    x.parent.color='b';
	                    w.right.color='b';
	                    leftRotate(x.parent);
	                    x=root;
	                }
	            }
	            else{
	                Node w=x.parent.left;
	                if(w.color=='r'){
	                    w.color='b';
	                    x.parent.color='r';
	                    rightRotate(x.parent);
	                    w=x.parent.left;
	                }
	                if(w.right.color=='b'&&w.left.color=='b'){
	                    w.color='r';
	                    x=x.parent;
	                }
	                else{
	                    if(w.left.color=='b'){
	                        w.right.color='b';
	                        w.color='r';
	                        leftRotate(w);
	                        w=x.parent.left;
	                    }
	                    w.color=x.parent.color;
	                    x.parent.color='b';
	                    w.left.color='b';
	                    rightRotate(x.parent);
	                    x=root;
	                }
	            }
	        }
	        x.color='b';
	    }
	    
	    //**** This part performs left rotation around pivot to correct black height property and ensure height balance ****
		
	    public void leftRotate(Node x){
	        Node y=x.right;
	        x.right=y.left;
	        if(y.left!=sentinel){
	            y.left.parent=x;
	        }
	        y.parent=x.parent;
	        if(x.parent==sentinel)
	            root=y;
	        else if(x==x.parent.left)
	            x.parent.left=y;
	        else
	            x.parent.right=y;
	        y.left=x;
	        x.parent=y;
	    }
	    
	    //**** This part performs right rotation around pivot to correct black height property and ensure height balance ****
		
	    public void rightRotate(Node x){
	        Node y=x.left;
	        x.left=y.right;
	        if(y.right!=sentinel){
	            y.right.parent=x;
	        }
	        y.parent=x.parent;
	        if(x.parent==sentinel)
	            root=y;
	        else if(x==x.parent.right)
	            x.parent.right=y;
	        else
	            x.parent.left=y;
	        y.right=x;
	        x.parent=y;
	    }
	   
	   //**** replaces one subtree as a child of its parent with another subtree ****
	   
	    public void transplant(Node u, Node v){
	        if (u.parent==sentinel)
	            root=v;
	        else if(u==u.parent.left)
	            u.parent.left=v;
	        else
	            u.parent.right=v;
	        v.parent=u.parent;
	    }
	    
		//*******************************************************************************************************************************************************
		
	    //**** recursively computes the smallest value just greater than or the largest value just less than the key and stores them in an array ****

		//*******************************************************************************************************************************************************
		
		
	    public Node[] successorPredecessor(Node root, Node[] presuc, int key){
	        if(root==sentinel){
	            return null;
	        }
	        if(root.ID==key){
	            if(root.left!=sentinel){
	                Node tmp=root.left;
	                while(tmp.right!=sentinel){
	                    tmp=tmp.right;
	                }
	                
	                presuc[0]=tmp;
	            }
	            if(root.right!=sentinel){
	                Node tmp=root.right;
	                while(tmp.left!=sentinel)
	                    tmp=tmp.left;
	                    presuc[1]=tmp;
	            }
	            return presuc;
	        }
	        else  if(root.ID>key){
	        	presuc[1]=root;
	        	successorPredecessor(root.left, presuc, key);
	        }
	        else if(root.ID<key){
	        	presuc[0]=root;
	        	successorPredecessor(root.right, presuc, key);
	        }
	        //returns array of nodes
	        return presuc;
	    }
	    
	   //**** prints the ID and the count of the event with the lowest ID that is greater that inputed ID ****
	   
	    public void next(int ID){
	    	//node array to store next and previous nodes
	        Node[] presuc=new Node[2];
	      
	        Node successor=successorPredecessor(root, presuc, ID)[1];
	        if (successor!=null)
	            System.out.println(successor.ID+" "+successor.count);
	        else
	        	//no next element
	            System.out.println("0 0");
	    }
	    
	    //**** prints the ID and the count of the event with the greatest ID that is lesser that inputed ID ****
		
	    public void previous(int ID){
	    	 Node[] presuc=new Node[2];
		      
		     Node predecessor=successorPredecessor(root, presuc, ID)[0];
		     if (predecessor!=null)
		            System.out.println(predecessor.ID+" "+predecessor.count);
		        else
		        	//no previous element
		            System.out.println("0 0");
	    }
	    
	    // **** searches for the node corresponding to the inputed key ****
		
	    public Node search(Node k,int x) {
	        
	        while (k!=sentinel){
	            if(k.ID==x)
	            	//key found returns node
	                return k;
	            else if(k.ID<x)
	                k=k.right;
	            else
	                k=k.left;
	        }
	        //key not found
	        return sentinel;
	    }

	    // **** increases the count of the event ID by m ****
		
	    public void increase(int ID, int m){
	    	//searches for the ID
	        Node input = search(root,ID);
	        if(input!=sentinel) {
	        	//if ID present then increase count
            System.out.println(input.count+=m);
	        }
	        else{
	        	//ID not present so insert it and then print count
	            insert(ID,m);
	            System.out.println(m);
	        }
	    }
	    
	  // **** decreases the count of the event ID by m ****
	  
	  
	    public void reduce(int ID, int m){
	    //searches for the ID
	        Node input = search(root,ID);
	        if(input!=sentinel) {
	            input.count -= m;
	            if (input.count > 0) {
	            	//display reduced count
	            	System.out.println(input.count);
	            } else {
	            	//if count becomes less than or equal to 0 then delete the node and print 0
	                delete(input);
	                System.out.println("0");
	            }
	        }
	        else
	        // ID not present so display 0
	            System.out.println("0");
	    }
	    
	    // **** display count of ID corresponding to input ****
		
	    public void count(int ID){
	        Node input=search(root,ID);
	        if(input!=sentinel) {
	            System.out.println(input.count);
	        }
	        else
	        	//ID not present
	            System.out.println("0");
	    }
	 
	    // **** recursively computes count of the nodes whose IDs lie between ID1 and ID2 inclusive ****
		
	    public int inrangeHelper(Node root, int ID1, int ID2) {
	       
	    	if(root==sentinel)
	            return 0;
	       
	        if(root.ID==ID1&&root.ID==ID2)
	            return root.count;

	        if(root.ID>=ID1 && root.ID<=ID2){
	        
	            return root.count+inrangeHelper(root.left,ID1,ID2)+inrangeHelper(root.right,ID1,ID2);
	        }
	        else if(root.ID<ID1)
	            return inrangeHelper(root.right,ID1,ID2);

	        
	        else 
	            return inrangeHelper(root.left, ID1, ID2);
	        
	    }
        
	    // **** Computes the inrange by calling the  helper function ****
		
	    public void inRange(int ID1, int ID2){
	        System.out.println(inrangeHelper(root, ID1, ID2));
	    }

  
	    
	 
}

