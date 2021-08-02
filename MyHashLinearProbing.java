public class MyHashLinearProbing {
    public Slot[] hashTable;

    public MyHashLinearProbing(Integer size) {
        this.hashTable = new Slot[size];
    }

    public class Slot {
        String key;
        String value;
        Slot(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public int hashFunc(String key) {
        return (int)(key.charAt(0)) % this.hashTable.length;
    }

    public boolean saveData(String key, String value) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
            if (this.hashTable[address].key == key) {
                this.hashTable[address].value = value;
                return true;
            } else {
                Integer currAddress = address + 1;
                while (this.hashTable[currAddress] != null) {   //해당 슬롯이 있는지 부터 확인
                    if (this.hashTable[currAddress].key == key) {   //있으면 그 때 비로소 해쉬테이블의 현재 어드레스에 있는 키값이 내가 찾고자하는 키값인지 보고
                        this.hashTable[currAddress].value = value;  // 맞다면 해쉬테이블에 현재 주소에 있는 값을 업데이트 하면 된다.
                        return true;
                    } else {
                        currAddress++;
                        if (currAddress >= this.hashTable.length) {
                            return false;
                        }
                    }
                }
                this.hashTable[currAddress] = new Slot(key, value);
                return true;
            }
        } else {
            this.hashTable[address] = new Slot(key, value);
        }
        return true;
    }

    public String getData(String key) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
            if (this.hashTable[address].key == key) {
                return this.hashTable[address].value;
            } else {
                Integer currAddress = address + 1;
                while (this.hashTable[currAddress] != null) {
                    if (this.hashTable[currAddress].key == key) {
                        return this.hashTable[currAddress].value;
                    } else {
                        currAddress++;
                        if (currAddress >= this.hashTable.length) {
                            return null;
                        }
                    }
                }
                return null;
            }
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        MyHashLinearProbing mainObject = new MyHashLinearProbing(20);

        mainObject.saveData("DaveLee", "01022223333");
        mainObject.saveData("Dave", "01055556666");
        mainObject.saveData("David", "01044445555");

        System.out.println(mainObject.getData("DaveLee"));
        System.out.println(mainObject.getData("Dave"));
        System.out.println(mainObject.getData("David"));

    }
}