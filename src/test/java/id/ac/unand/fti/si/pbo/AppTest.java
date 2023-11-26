package id.ac.unand.fti.si.pbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void testCanDepositInterfaceExists(){
        try {
            Class<?> depositIface = Class.forName("id.ac.unand.fti.si.pbo.CanDeposit");
            Class<?>[] params = {Integer.class};
            depositIface.getMethod("deposit", params);
            depositIface.getMethod("tarikTunai", params);
            assertTrue(true);
        } catch (ClassNotFoundException e) {
            assertTrue(false, "Interface CanDeposit tidak ditemukan");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            assertTrue(false, "Method deposit/tarikTunai tidak ditemukan");
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCicilanInterfaceExists(){
        try {
            Class<?> cicilanIface = Class.forName("id.ac.unand.fti.si.pbo.CanRequestCicilan");
            Class<?>[] params = {Integer.class, Integer.class};
            cicilanIface.getMethod("hitungCicilanPerBulan", params);
            assertTrue(true);
        } catch (ClassNotFoundException e) {
            assertTrue(false, "Interface CanRequestCicilan tidak ditemukan");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            assertTrue(false, "Method hitungCicilanPerBulan tidak ditemukan");
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRequestDeliveryInterfaceExists(){
        try {
            Class<?> cicilanIface = Class.forName("id.ac.unand.fti.si.pbo.CanRequestDelivery");
            Class<?>[] params = {Double.class};
            cicilanIface.getMethod("hitungOngkir", params);
            assertTrue(true);
        } catch (ClassNotFoundException e) {
            assertTrue(false, "Interface CanRequestDelivery tidak ditemukan");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            assertTrue(false, "Method hitungOngkir tidak ditemukan");
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetDiskonInterfaceExists(){
        try {
            Class<?> cicilanIface = Class.forName("id.ac.unand.fti.si.pbo.CanGetDiskon");
            Class<?>[] params = {Integer.class};
            cicilanIface.getMethod("hitungTotalBayar", params);
            assertTrue(true);
        } catch (ClassNotFoundException e) {
            assertTrue(false, "Interface CanGetDiskon tidak ditemukan");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            assertTrue(false, "Method hitungTotalBayar() tidak ditemukan");
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testKelasMemberRegulerExists(){
        try {
            Class<?> regulerClass = Class.forName("id.ac.unand.fti.si.pbo.MemberReguler");

            MemberReguler member = new MemberReguler();
            assertTrue(member instanceof Member);
            assertTrue(member instanceof CanGetDiskon);

            assertEquals(0, member.getPoin());

            assertEquals(50000, member.hitungTotalBayar(50000));
            assertEquals(5, member.getPoin());

            assertEquals(544500, member.hitungTotalBayar(550000));
            assertEquals(59, member.getPoin());

            assertEquals(1960000, member.hitungTotalBayar(2000000));
            assertEquals(255, member.getPoin());

            assertEquals(7760000, member.hitungTotalBayar(8000000));
            assertEquals(1031, member.getPoin());

        } catch (ClassNotFoundException e) {
            assertTrue(false, "Kelas MemberReguler tidak ditemukan");
            e.printStackTrace();
        }
    }

    @Test
    public void testKelasMemberSilverExists(){
        try {

            Class<?> silverClass = Class.forName("id.ac.unand.fti.si.pbo.MemberSilver");
            MemberSilver member = new MemberSilver();
            assertTrue(member instanceof Member);
            assertTrue(member instanceof CanGetDiskon);
            assertTrue(member instanceof CanRequestDelivery);

            assertEquals(0, member.getPoin());

            assertEquals(50000, member.hitungTotalBayar(50000));
            assertEquals(5, member.getPoin());

            assertEquals(544500, member.hitungTotalBayar(550000));
            assertEquals(59, member.getPoin());

            assertEquals(1940000, member.hitungTotalBayar(2000000));
            assertEquals(253, member.getPoin());

            assertEquals(7600000, member.hitungTotalBayar(8000000));
            assertEquals(1013, member.getPoin());

            assertEquals(0, member.hitungOngkir(4.9));
            assertEquals(4500, member.hitungOngkir(7.3));

        } catch (ClassNotFoundException e) {
            assertTrue(false, "Kelas MemberReguler tidak ditemukan");
            e.printStackTrace();
        }

    }

    @Test
    public void testKelasMemberGoldExists(){
        try {
            Class<?> goldClass = Class.forName("id.ac.unand.fti.si.pbo.MemberGold");

            MemberGold member = new MemberGold();
            assertTrue(member instanceof Member);
            assertTrue(member instanceof CanGetDiskon);
            assertTrue(member instanceof CanRequestDelivery);
            assertTrue(member instanceof CanRequestCicilan);

            assertEquals(0, member.getPoin());

            assertEquals(50000, member.hitungTotalBayar(50000));
            assertEquals(5, member.getPoin());

            assertEquals(539000, member.hitungTotalBayar(550000));
            assertEquals(58, member.getPoin());

            assertEquals(1920000, member.hitungTotalBayar(2000000));
            assertEquals(250, member.getPoin());

            assertEquals(7520000, member.hitungTotalBayar(8000000));
            assertEquals(1002, member.getPoin());

            assertEquals(0, member.hitungOngkir(6.9));
            assertEquals(3000, member.hitungOngkir(9.3));

        } catch (ClassNotFoundException e) {
            assertTrue(false, "Kelas MemberReguler tidak ditemukan");
            e.printStackTrace();
        }

    }

    @Test
    public void testKelasMemberPlatinumExists(){
        try {
            Class<?> platinumClass = Class.forName("id.ac.unand.fti.si.pbo.MemberPlatinum");

            MemberPlatinum member = new MemberPlatinum();
            assertTrue(member instanceof Member);
            assertTrue(member instanceof CanGetDiskon);
            assertTrue(member instanceof CanRequestDelivery);
            assertTrue(member instanceof CanRequestCicilan);
            assertTrue(member instanceof CanDeposit);

            assertEquals(0, member.getPoin());

            assertEquals(50000, member.hitungTotalBayar(50000));
            assertEquals(5, member.getPoin());

            assertEquals(533500, member.hitungTotalBayar(550000));
            assertEquals(58, member.getPoin());

            assertEquals(1900000, member.hitungTotalBayar(2000000));
            assertEquals(248, member.getPoin());

            assertEquals(7439999, member.hitungTotalBayar(8000000));
            assertEquals(991, member.getPoin());

            assertEquals(10234650, member.hitungTotalBayar(11005000));
            assertEquals(2014, member.getPoin());

            assertEquals(0, member.hitungOngkir(6.9));
            assertEquals(3000, member.hitungOngkir(12.3));

            assertEquals(0, member.cekSaldo());
            member.deposit(400515);
            assertEquals(400515, member.cekSaldo());
            member.tarikTunai(10515);
            assertEquals(390000, member.cekSaldo());

        } catch (ClassNotFoundException e) {
            assertTrue(false, "Kelas MemberPlatinum tidak ditemukan");
            e.printStackTrace();
        }

    }
}
