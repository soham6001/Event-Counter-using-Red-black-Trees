

public class Node {

	public Node left;
	public Node right;
	public Node parent;
	public char color;
	public int ID;
	public int count;
	
    //*******************************************************************************************************************************************************
	
	//**** Initializes the Nodes by coloring all of them black ****
	
	//*******************************************************************************************************************************************************
	
	public Node(int ID, int count) {
		this.ID = ID;
		this.count = count;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.color = 'b';
	}

}