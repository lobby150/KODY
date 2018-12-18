package harddrive;

import java.util.ArrayList;
import java.util.List;

public class HardDrive {
public static byte[] disk=new byte[1024];
    public static int blockSize = 32;
    public static int blockCount = 32;
    public static iNode[] iNodeTable = new iNode[32];
    public static boolean[] table=new boolean[32];
    public static List<Catalog> catalog = new ArrayList<>();

    
    // KLASY DO INTERFEJSU
    // Create File (2 opcje z nazwa, lub nazwa i dane) - tworzenie pliku
    // Show free space - ilosć wolnego miejsca
    // Show file list - lista plików
    // Delete file - usuwanie pliku
    // Edit file - dodanie rzeczy do pliku
    // Show file - wyswietla dane pliku (zawartość)
    // Get Data - zwraca dane 
    // Rename File - zmiana nazwy
    // Display info - pokazuje nazwe, inode, bloki
    // Show Disc - show file ale dla wszystkich plikow
    //
    
    //TO DO
    public boolean CreateFile(String fileName)
    {
        boolean check = IfFileExists(fileName);
        if(!check)
        {
            
            return true;
        }
        
        else
        {
            System.out.println("Blad. Istnieje plik o takiej nazwie");
        }
        return false; //
    }
    //TO DO
    public boolean CreateFile(String fileName, String Data)
    {
        boolean check = IfFileExists(fileName);
        if(!check)
        {
            
            return true;
        }
        
        else
        {
            System.out.println("Blad. Istnieje plik o takiej nazwie");
        }
        
        return false;
    }
    //DONE
    public void ShowFreeSpace()
    {
        System.out.println("Wolne miesjce na dysku to: " + GetFreeBlocks() * getBlockSize()+ "\n" + "Wolne bloki to: " +GetFreeBlocks() + "\n");
    }
    //DONE
    public void ShowFileList()
    {
        if(catalog.size()<1)
            {
                System.out.println("Brak plikow");
            }
            else
            {
                for(Catalog c:catalog)
                {
                    System.out.println(c.getName()+"\n");
                }
            }
    }
    //TO DO
    public boolean DeleteFile(String fileName)
    {
        //CHUJ KURWA SEMAFOR
        boolean check = IfFileExists(fileName);
        if(!check)
        {
            
            return true;
        }
        
        else
        {
            System.out.println("Blad. Nie istnieje plik o takiej nazwie");
        }
        
        return false;
    }
    //TO DO
    public boolean EditFile(String fileName)
    {
        //CHUJ KURWA SEMAFOR
        boolean check = IfFileExists(fileName);
        if(!check)
        {
            
            return true;
        }
        
        else
        {
            System.out.println("Blad. Nie istnieje plik o takiej nazwie");
        }
        
        return false;
    }
    //DONE
    public void ShowFile(String fileName)
    {
        boolean check = IfFileExists(fileName);
        if(check)
        {
            iNode temp= null;
            for(Catalog p:catalog)
            {
                if(p.getName().equals(fileName))
                {
                    temp = iNodeTable[p.getIndexiNode()];
                }
            }
            
            System.out.println("Zawartosc pliku " + fileName + GetData(fileName));
        }
        
        else
        {
            System.out.println("Blad. Nie istnieje plik o takiej nazwie");
        }
        
    }
    //TO DO
    public String GetData(String fileName)
    {
        String Data = "";
        boolean check = IfFileExists(fileName);
        if(check)
        {
            iNode temp= null;
            for(Catalog p:catalog)
            {
                if(p.getName().equals(fileName))
                {
                    temp = iNodeTable[p.getIndexiNode()];
                }
            }
            
            //Data=read inode
        }
        
        else
        {
            System.out.println("Blad. Nie istnieje plik o takiej nazwie");
        }
        
        return Data;
    }
    //TO DO
    public boolean Rename(String fileName, String newName)
    {
        //CHUJ KURWA SEMAFOR
        boolean check = IfFileExists(fileName);
        if(check)
        {
            boolean checkNew = IfFileExists(newName);
            if(!checkNew)
            {
                for(Catalog c:catalog)
                {
                    if(c.getName().equals(fileName))
                    {
                        c.setName(newName);
                    }
                }
                return true;
            }
            else
            {
                System.out.println("Blad. Istnieje plik o takiej nazwie");
            }
            
        }
        
        else
        {
            System.out.println("Blad. Nie istnieje plik o takiej nazwie");
        }
        
        return false;
    }    
    //DONE
    public void DisplayInfo(String fileName)
    {
        boolean check = IfFileExists(fileName);
        if(check)
        {
            iNode temp= null;
            for(Catalog p:catalog)
            {
                if(p.getName().equals(fileName))
                {
                    temp = iNodeTable[p.getIndexiNode()];
                }
            }
            
            System.out.println("Rozmiar pliku to: " + temp.getFileSize() + "\n");
            System.out.println("Liczba blokow to: " + temp.getBlockCounter() + "\n");
            System.out.println("Indeks bloku posredniego " + temp.getNumberOfIndirectBlock() + "\n"); //to jest chyba zle
        }
        
        else
        {
            System.out.println("Blad. Nie istnieje plik o takiej nazwie");
        }
    }
    //TO DO
    public boolean FormatDrive()
    {
        //CHUJ KURWA SEMAFOR
        for(int i=0; i< 32; i++)
        {           
            disk[i]=-1;
        }
        for(int i=0; i<iNodeTable.length; i++)
        {
            iNodeTable[i]=null;
        }
        
        for(int i=0; i<32; i++)
        {
            table[i]=true;
        }
        return true;    
    }
    //DONE
    public void ShowDisc()
    {
        for(int i=0; i<32; i++)
        {
            if(table[i]==false)
            {
                System.out.println("Zawartosc bloku " + i);
                for(int j=0; j<32; j++)
                {
                    System.out.println(disk[i*32+j]);
                }
                System.out.println("\n");
            }
            else
            {
                System.out.println("Blok " + i + " jest pusty \n");
            }
        }
    }
       
    // KONIEC KLAS DO INTERFEJSU 
    // W TEORII RESZTA MOGŁABY BYC PRIVATE
    // ALE W SUMIE NIE WIEM WIEC DAM PUBLIC
    //
    //
    
    //DONE
    public HardDrive() {
        for(int i=0; i< 32; i++)
        {           
            disk[i]=-1;
        }
        for(int i=0; i<iNodeTable.length; i++)
        {
            iNodeTable[i]=null;
        }
        
        for(int i=0; i<32; i++)
        {
            table[i]=true;
        }
    }
    //DONE
    public static int getBlockCount() {
        return blockCount;
    }
    //DONE
    public boolean IfFileExists(String fileName)
    {
        boolean result;
        result=false;
        for(Catalog p:catalog)
        {
            if(p.getName().equals(fileName))
            {
                result = true;
                break;
            }
        }
        return result;
    }
    //DONE
    public int GetFreeBlocks()
    {
        int result=0;
        for (boolean i:table)
        {
            if(i==false)
            {
                result++;
            }
        }
        return result;
    }
    //DONE
    public static int getBlockSize() {
        return blockSize;
    }
    //DONE
    private static int GetFirstFreeBlock()
    {
        int result =-1;
        for(int i=0; i<32; i++)
        {
            if(table[i]==true)
            {
                result=i;
                break;
            }
        }
        return result;
    }
    
    
}
