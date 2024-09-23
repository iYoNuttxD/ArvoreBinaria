package ArvoreBinaria;

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Inserção
        System.out.println("Inserindo elementos na árvore...");
        int[] elementsToInsert = {10, 5, 15, 3, 7, 12, 18, 2, 4, 6, 8, 11, 13, 17, 20};
        for (int element : elementsToInsert) {
            tree.insert(element);
        }

        System.out.println("Árvore após inserções:");
        tree.printTree();

        // Teste de remoção de um nó folha
        try {
            tree.remove(2);
            System.out.println("Árvore após remover 2 (folha):");
            tree.printTree();
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        // Teste de remoção de um nó com um filho
        try {
            tree.remove(7); // Nó com um filho
            System.out.println("Árvore após remover 7 (com dois filhos):");
            tree.printTree();
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        // Teste de remoção de um nó com dois filhos
        try {
            tree.remove(10); // Nó com dois filhos
            System.out.println("Árvore após remover 10 (com dois filhos):");
            tree.printTree();
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        // Teste de remoção de um nó que não existe
        try {
            tree.remove(100); // Nó que não existe
        } catch (IllegalAccessException e) {
            System.out.println("Tentativa de remoção de nó não existente: " + e.getMessage());
        }

        // Remoção de mais nós
        try {
            tree.remove(15);
            System.out.println("Árvore após remover 15 (com dois filhos):");
            tree.printTree();
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        try {
            tree.remove(12);
            System.out.println("Árvore após remover 12 (com um filho):");
            tree.printTree();
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        try {
            tree.remove(18);
            System.out.println("Árvore após remover 18 (folha):");
            tree.printTree();
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        // Removendo todos os nós restantes
        try {
            tree.remove(3);
            tree.remove(4);
            tree.remove(6);
            tree.remove(8);
            tree.remove(11);
            tree.remove(13);
            tree.remove(17);
            tree.remove(20);
            System.out.println("Árvore após remover todos os nós restantes:");
            tree.printTree();
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        // Removendo o último nó
        try {
            tree.remove(5);
            System.out.println("Árvore após remover 5 (último nó):");
            tree.printTree();
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        // Teste de remoção em árvore vazia
        try {
            tree.remove(5);
            System.out.println("Tentativa de remoção em árvore vazia:");
        } catch (IllegalAccessException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}


