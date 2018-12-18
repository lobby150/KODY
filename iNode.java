package harddrive;

public class iNode {
    private int fileSize;
    private int blockCounter;
    private byte directBlock1;
    private byte directBlock2;
    private int index;
    int numberOfIndirectBlock;

    public iNode(int fileSize, int blockCounter, byte directBlock1, byte directBlock2, int index, int numberOfIndirectBlock) {
        this.fileSize = fileSize;
        this.blockCounter = blockCounter;
        this.directBlock1 = directBlock1;
        this.directBlock2 = directBlock2;
        this.index = index;
        this.numberOfIndirectBlock = numberOfIndirectBlock;
    }

    public int getBlockCounter() {
        return blockCounter;
    }

    public byte getDirectBlock1() {
        return directBlock1;
    }

    public byte getDirectBlock2() {
        return directBlock2;
    }

    public int getIndex() {
        return index;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setDirectBlock2(byte directBlock2) {
        this.directBlock2 = directBlock2;
    }

    public int getNumberOfIndirectBlock() {
        return numberOfIndirectBlock;
    }

    public void setNumberOfIndirectBlock(int numberOfIndirectBlock) {
        this.numberOfIndirectBlock = numberOfIndirectBlock;
    }

    public void ResizeFileSize(int sizeToAdd) {
        fileSize = fileSize+sizeToAdd;
    }

}
