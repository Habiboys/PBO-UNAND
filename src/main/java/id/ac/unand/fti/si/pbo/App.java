package id.ac.unand.fti.si.pbo;

public class App {
    
    public static void main(String[] args) {
    
    MemberPlatinum mplatinum = new MemberPlatinum();
    MemberGold mgold = new MemberGold();
    MemberSilver msilver = new MemberSilver();


        



    //Nama: Muhammad Nouval Habibie
    // Nim: 2211521020
    // ini adalah bagian polymorphism 
    // karena dalam sebuah polymorphisme antar kelas dapat memiliki method yang sama
    // namun perilaku atau implement methodnya tersebut  berbeda, sehingga menyebabkan outputnya pun berbeda
    // disini terlihat  masing masing object yang dibuat dari kelas berbeda 
    // ketika memanggil method yang sama, maka akan memberikan outpputnya berbeda
    System.out.println("Total Bayar Platinum Rp."+ mplatinum.hitungTotalBayar(1000000));
    System.out.println("Total Bayar Gold Rp"+ mgold.hitungTotalBayar(1000000));
    System.out.println("Total Bayar Silver Rp."+ msilver.hitungTotalBayar(1000000));



    }
}