package id.ac.unand.fti.si.pbo;

public class MemberGold implements Member, CanGetDiskon,CanRequestDelivery,CanRequestCicilan{
  private int poin;
  private double totalBelanja;
  private int jarakTempuh;
  private int cicilanBulan;
 

    public MemberGold(){
        
    }

  public MemberGold(int jarakTempuh, int cicilanBulan) {
      this.poin = 0;
      this.totalBelanja = 0;
      this.jarakTempuh = jarakTempuh;
      this.cicilanBulan = cicilanBulan;
  }

  public void belanja(double jumlahBelanja) {
      this.totalBelanja += jumlahBelanja;
      this.poin += (int) (jumlahBelanja / 10000); // 1 poin setiap kelipatan Rp 10,000
  }

  public void redeemPoin(Integer jumlahPoin) {
      if (jumlahPoin <= poin) {
          this.poin -= jumlahPoin;
          System.out.println("Poin berhasil diredeem!");
      } else {
          System.out.println("Poin tidak mencukupi untuk redeem.");
      }
  }

  public Integer getPoin() {
    return this.poin;
}

  public Integer hitungTotalBayar(Integer totalBelanja) {
    double diskon = 0;
    
    if (totalBelanja >= 500000 && totalBelanja < 1000000) {
        diskon = 0.02; // 1% diskon
        totalBelanja=(int) (totalBelanja * (1 - diskon));
        this.poin+=totalBelanja/10000;
    } else if (totalBelanja >= 1000000 && totalBelanja < 7000000) {
        diskon = 0.04; // 2% diskon
        totalBelanja=(int) (totalBelanja * (1 - diskon));
        this.poin+=totalBelanja/10000;

    } else if (totalBelanja >= 7000000) {
        diskon = 0.06; // 3% diskon
        totalBelanja=(int) (totalBelanja * (1 - diskon));
        this.poin+=totalBelanja/10000;
    }
    else {
        diskon=0;
        this.poin+=totalBelanja/10000;
    }
    return totalBelanja;
}
public Double hitungOngkir(Double jarak) {
    //jika < 5 km dari toko. Kelebihan jarak akan dikenakan biaya Rp 1.500/km
    Double ongkir;
    if(jarak>7){
        if(jarak%1!=0){
        ongkir=(jarak-6-(jarak%1))*1000;
        }
        else{
        ongkir=(jarak-7)*1000;
        }
    }
    else{
        ongkir=0.0;
    }
    return ongkir;
}
public Integer hitungCicilanPerBulan(Integer amount, Integer time){
    return cicilanBulan;
}

}
