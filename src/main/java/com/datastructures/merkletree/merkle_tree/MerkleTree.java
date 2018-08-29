package com.datastructures.merkletree.merkle_tree;

/**
 * @author vikash singh 
 *
 */
import lombok.*;
import java.security.MessageDigest;
import org.apache.commons.codec.digest.*;

@Getter@Setter
public class MerkleTree 
{

	private MerkleTree leftSubTree;
	private MerkleTree rightSubTree;
	
	private Leaf leftLeaf;
	private Leaf rightLeaf;
	
	// ALgorith that will be used for hashing
	private final MessageDigest hashAlgo;
	
	//hash value of current node
	byte[] hashValue;
	
	public MerkleTree(MessageDigest md) {
		hashAlgo=md;
		leftSubTree = null;
		rightLeaf = null;
		leftLeaf = null;
		rightSubTree = null;
	}
	
	//Calculate digest from left and right subtrees
	public void calculateDigest(MerkleTree left, MerkleTree right) {
		
		//update tree structure
		this.leftSubTree = left;
		this.rightSubTree = right;
		
		//update data to be digested
		hashAlgo.update(left.hashValue);
		hashAlgo.update(right.hashValue);
		
		//calculating hash value
		hashValue = hashAlgo.digest();
		
	}
	
	//Calculate Digest from Leaves
	public void calculateDigest(Leaf left, Leaf right) {
		
		//update tree structure
		this.leftLeaf = left;
		this.rightLeaf = right;
		
		//update data to be digested
		hashAlgo.update(left.hashValue);
		hashAlgo.update(right.hashValue);
		
		//calculating hash value
		hashValue = hashAlgo.digest();
	}
	
	//Hexadecimal string representation of hash value
	@Override
	public String toString(){
		StringBuffer hexString = new StringBuffer();
		
		/*
		 * Java first converts your value to an integer, and then performs sign extension 
		 * So while you would expect 1111 1110 >> 4 to be 0000 1111,
		 * in reality, in Java it is represented as the two's complement 0xFFFFFFFF!
		 * So, we have to shift and mask.
		 * Character.forDigit((bytes[0] >> 4) & 0xF, 16);
		 */
		
		for(int i=0; i < hashValue.length; i++){
			hexString.append(Character.forDigit((hashValue[i] >> 4) & 0xF, 16));
			hexString.append(Character.forDigit((hashValue[i] & 0xF), 16));
		}
		return hexString.toString();
	}
	
}
