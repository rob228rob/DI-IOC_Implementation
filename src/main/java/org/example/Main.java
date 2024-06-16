package org.example;

import not_used.SearchTree.AbstractSearchTree;
import not_used.SearchTree.BinarySearchTree;
import not_used.SearchTree.Node;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

    }



    public void test_bst()
    {
        AbstractSearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();

        for (int i = 0; i < 100; ++i) {
            bst.insertNode(new Node<Integer, Integer>(i, i * 10 - 109 * 29));
        }

        var newNode = bst.findNode(41);
        System.out.println("key: " + newNode.getKey() + " ; value: " + newNode.getValue());

        bst.removeNode(41);

        try
        {
            var newNode1 = bst.findNode(41);
            System.out.println("key: " + newNode1.getKey() + " ; value: " + newNode1.getValue());
        }
        catch (Exception e)
        {
            System.out.println("error occured here: " + e.getMessage());
        }
    }

}