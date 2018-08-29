package com.datastructures.merkletree.merkle_tree;

import java.util.List;

public interface TreeBuilder {
	
	public void addToLeaf(Leaf leaf,byte[] data);
	public void addLeafToTree(MerkleTree tree,Leaf leaf);
	public void createLeaf(List<byte[]> dataBytes);
	public void createEmptyLeaf();
	public void createNode();
	
}
