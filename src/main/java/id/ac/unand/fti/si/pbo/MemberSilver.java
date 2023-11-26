package id.ac.unand.fti.si.pbo;

public class MemberSilver implements Member, CanGetDiskon,CanRequestDelivery {
  private Integer poin=0;
    private double totalBelanja;
    private Integer jarakTempuh;

    public MemberSilver(){

    }

    public MemberSilver(Integer jarakTempuh) {
        this.poin = 0;
        this.jarakTempuh = jarakTempuh;
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
            diskon = 0.01; // 1% diskon
            totalBelanja=(int) (totalBelanja * (1 - diskon));
            this.poin+=totalBelanja/10000;
        } else if (totalBelanja >= 1000000 && totalBelanja < 7000000) {
            diskon = 0.03; // 2% diskon
            totalBelanja=(int) (totalBelanja * (1 - diskon));
            this.poin+=totalBelanja/10000;
 
        } else if (totalBelanja >= 7000000) {
            diskon = 0.05; // 3% diskon
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
        if(jarak>5){
            if(jarak%1!=0){
            ongkir=(jarak-4-(jarak%1))*1500;
            }
            else{
            ongkir=(jarak-5)*1500;
            }
        }
        else{
            ongkir=0.0;
        }
        return ongkir;
    }
}