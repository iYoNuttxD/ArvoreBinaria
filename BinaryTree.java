package ArvoreBinaria;

import java.util.ArrayList;
import java.util.List;

class Node {
    public Node right;
    public int element;
    public Node left;

    Node(int element) {
        this.element = element;
    }
}

public class BinaryTree {
    private Node root;
    private int length;


    public void insert(int element) {
        Node node = new Node(element);
        if (root == null) {
            root = node;
        } else {
            Node location = insertNode(root, element);
            if (element > location.element) {
                location.right = node;
            } else {
                location.left = node;
            }
        }
        length++;
    }

    private Node insertNode(Node current, int element) {
        //Busca o nó pai onde iremos inserir
        if (element > current.element) {
            if (current.right == null) {
                return current;
            } else {
                return insertNode(current.right, element);
            }
        } else {
            if (current.left == null) {
                return current;
            } else {
                return insertNode(current.left, element);
            }
        }
    }

    public void remove(int element) throws IllegalAccessException {
        if (root == null) {
            throw new IllegalAccessException("Árvore Vazia");
        } else if (root.element == element) {
            if (root.right == null && root.left == null) {
                root = null;
            } else if (root.left == null) {
                root = root.right;
            } else if (root.right == null) {
                root = root.left;
            } else {
                Node sucessor = removeNodeComplex(root);
                if (sucessor != null) {
                    remove(sucessor.element);
                    root.element = sucessor.element;
                }
            }
            length--;
        } else {
            Node nodeFather = removeNode(root, element);
            if (nodeFather == null) {
                throw new IllegalAccessException("Nó inválido");
                // Verifica se o nó a ser removido está à esquerda ou à direita
            } else if (nodeFather.right != null && nodeFather.right.element == element) {
                Node nodeSon = nodeFather.right;
                //Deleta se for uma folha
                if (nodeSon.left == null && nodeSon.right == null) {
                    nodeFather.right = null;
                    //Próximos dois else if, deleta o nó com 1 filho
                } else if (nodeSon.left == null) {
                    nodeFather.right = nodeSon.right;
                } else if (nodeSon.right == null){
                    nodeFather.right = nodeSon.left;
                } else {
                    //Remoção de nó com 2 filhos
                    Node sucessor = removeNodeComplex(nodeSon);
                    if (sucessor != null) {
                        remove(sucessor.element);
                        nodeSon.element = sucessor.element;
                    }
                }
            } else if (nodeFather.left != null && nodeFather.left.element == element) {
                Node nodeSon = nodeFather.left;
                //Deleta se for uma folha
                if (nodeSon.left == null && nodeSon.right == null) {
                    nodeFather.left = null;
                    //Próximos dois else if, deleta o nó com 1 filho
                } else if (nodeSon.left == null) {
                    nodeFather.left = nodeSon.right;
                } else if (nodeSon.right == null){
                    nodeFather.left = nodeSon.left;
                } else {
                    //Remoção de nó com 2 filhos
                    Node sucessor = removeNodeComplex(nodeSon);
                    if (sucessor != null) {
                        remove(sucessor.element);
                        nodeSon.element = sucessor.element;
                    }
                }
            }
            length--;
        }
    }

    private Node removeNode(Node current, int element) {
        //Busca o pai do nó que iremos remover
        if (element > current.element) {
            if (current.right != null) {
                if (current.right.element == element) {
                    return current;
                } else {
                    return removeNode(current.right, element);
                }
            } else {
                return null;
            }
        } else {
            if (current.left != null) {
                if (current.left.element == element) {
                    return current;
                } else {
                    return removeNode(current.left, element);
                }
            } else {
                return null;
            }
        }
    }

    private Node removeNodeComplex(Node current) {
        if (current.right == null) {
            return null;
        }
        Node successor = current.right;
        //Busca o nó mais a esquerda da direita
        while (successor.left != null) {
            successor = successor.left;
        }
        return successor;
    }

    public BinaryTree() {
        root = null;
        length = 0;
    }

    public void printTree() {
        List<List<String>> lines = new ArrayList<>();
        List<Node> level = new ArrayList<>();
        List<Node> next = new ArrayList<>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();

            nn = 0;

            for (Node n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = String.valueOf(n.element);
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.left);
                    next.add(n.right);

                    if (n.left != null) nn++;
                    if (n.right != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<Node> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perPiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perPiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    if (line.get(j) == null) {
                        for (int k = 0; k < perPiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perPiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perPiece / 2f - f.length() / 2f);

                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perPiece /= 2;
        }
    }
}
