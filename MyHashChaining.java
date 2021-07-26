public class MyHashChaining {
    public Slot[] hashTable;

    public MyHashChaining(Integer size) {
        this.hashTable = new Slot[size];
    }

    public class Slot {
        String key;
        String value;
        Slot next;
        Slot(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public int hashFunc(String key) {
        return (int)(key.charAt(0)) % this.hashTable.length;
    }

    public boolean saveData(String key, String value) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
            Slot findSlot = this.hashTable[address];
            Slot prevSlot = this.hashTable[address];    //링크드 리스트의 헤드 설정
            while (findSlot != null) {
                if (findSlot.key == key) {
                    findSlot.value = value;
                    return true;
                } else {
                    prevSlot = findSlot;
                    findSlot = findSlot.next;
                }
            }
            prevSlot.next = new Slot(key, value);
        } else {
            this.hashTable[address] = new Slot(key, value);
        }
        return true;
    }

    public String getData(String key) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
            Slot findSlot = this.hashTable[address];
            while (findSlot != null) {
                if (findSlot.key == key) {
                    return findSlot.value;
                } else {
                    findSlot = findSlot.next;

                }
            }
            return null;

        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        MyHashChaining mainObject = new MyHashChaining(20);

        mainObject.saveData("DaveLee", "01022223333");
        mainObject.saveData("Dave", "01055556666");
        mainObject.saveData("David", "01044445555");

        System.out.println(mainObject.getData("DaveLee"));
        System.out.println(mainObject.getData("Dave"));
        System.out.println(mainObject.getData("David"));

    }
}