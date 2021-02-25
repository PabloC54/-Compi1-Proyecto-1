package util;

import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Gilbert Le Blanc
 * https://stackoverflow.com/questions/23804675/list-files-and-directories-with-jtree-and-file-in-java
 */
public class CreateChildNodes {

    private DefaultMutableTreeNode root;

    private File fileRoot;

    public CreateChildNodes(File fileRoot,
        DefaultMutableTreeNode root) {
        this.fileRoot = fileRoot;
        this.root = root;
        createChildren(this.fileRoot, this.root);
    }


    private void createChildren(File fileRoot, DefaultMutableTreeNode node) {
        File[] files = fileRoot.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isFile()) {
                String name = file.getName().toLowerCase();
                if (!name.endsWith(".jpg") && !name.endsWith(".png") && !name.endsWith(".html")) {
                    continue;
                }
            }

            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new FileNode(file));
            node.add(childNode);
            if (file.isDirectory()) {
                createChildren(file, childNode);
            }
        }
    }
}
