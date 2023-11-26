package id.ac.unand.fti.si.pbo;

public class MemberReguler implements Member, CanGetDiskon {
  
    private Integer poin;
    private Integer totalBelanja;

    public MemberReguler() {
        this.poin = 0;
        this.totalBelanja = 0;
    }

    public void belanja(Integer jumlahBelanja) {
        this.totalBelanja += jumlahBelanja;
        this.poin += (jumlahBelanja / 10000); // 1 poin setiap kelipatan Rp 10,000
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

    @Override
    public Integer hitungTotalBayar(Integer totalBelanja) {
        double diskon = 0;
        
        if (totalBelanja >= 500000 && totalBelanja < 1000000) {
            diskon = 0.01; // 1% diskon
            totalBelanja=(int) (totalBelanja * (1 - diskon));
            this.poin+=totalBelanja/10000;
        } else if (totalBelanja >= 1000000 && totalBelanja < 7000000) {
            diskon = 0.02; // 2% diskon
            totalBelanja=(int) (totalBelanja * (1 - diskon));
            this.poin+=totalBelanja/10000;

        } else if (totalBelanja >= 7000000) {
            diskon = 0.03; // 3% diskon
            totalBelanja=(int) (totalBelanja * (1 - diskon));
            this.poin+=totalBelanja/10000;
        }
        else {
            diskon=0;
            this.poin+=totalBelanja/10000;
        }
        return totalBelanja;
    }
}
