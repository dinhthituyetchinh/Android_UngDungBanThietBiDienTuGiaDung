package com.example.androidmobile.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "QL_DoGiaDung", null, 12);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String dbNhacc ="create table nhacc( manhacc integer PRIMARY KEY autoincrement,\n" +
                "    tennhacc TEXT,\n" +
                "    email TEXT,\n" +
                "    sdt TEXT)";
        sqLiteDatabase.execSQL(dbNhacc);


        String dbSanPham = "create table sanpham(masp integer primary key autoincrement,tensp text, giasp integer,loaisp text, motasp text,manhacc integer references nhacc(manhacc) )";
        sqLiteDatabase.execSQL(dbSanPham);

        String dbAdmin = "create table admin(username text primary key ,hoten text,password text )";
        sqLiteDatabase.execSQL(dbAdmin);

        String dbKhachHang = "create table khachhang(username text primary key ,hoten text,password text,sdt text,diachi text )";
        sqLiteDatabase.execSQL(dbKhachHang);


        String dbHoaDon = "create table hoadon(mahd integer primary key autoincrement,makh text references khachhang(username),masp integer references sanpham(masp),tienhoadon intteger )";
        sqLiteDatabase.execSQL(dbHoaDon);


///////////test hóa đơn

        String dbHoaDon1 = "CREATE TABLE hoadon1 (mahd INTEGER PRIMARY KEY AUTOINCREMENT, makh TEXT REFERENCES khachhang(username), tienhoadon INTEGER)";
        sqLiteDatabase.execSQL(dbHoaDon1);

        String dbChiTietHD = "CREATE TABLE chitiethd (id INTEGER PRIMARY KEY AUTOINCREMENT, mahd INTEGER REFERENCES hoadon(mahd), masp INTEGER REFERENCES sanpham(masp), giasp INTEGER)";
        sqLiteDatabase.execSQL(dbChiTietHD);

//////////////////////////////////////////////////


        String dbSanPhamGh = "create table sanphamgh(masp integer primary key ,tensp text, giasp integer,loaisp text, motasp text,manhacc integer references nhacc(manhacc) )";
        sqLiteDatabase.execSQL(dbSanPhamGh);

        String dbSanPhamYT = "create table sanphamyt(masp integer primary key ,tensp text, giasp integer,loaisp text, motasp text,manhacc integer references nhacc(manhacc) )";
        sqLiteDatabase.execSQL(dbSanPhamYT);


        String dbthongbap = "create table thongbao(matb integer primary key autoincrement,thongtin text ,makh text references khachhang(username))";
        sqLiteDatabase.execSQL(dbthongbap);


        sqLiteDatabase.execSQL("insert into nhacc values (1,'Panasonic','panasonic@gmail.com','0123456789')," +
                "(2,'philips','philips@gmail.com','0389456712'),(3,'Elmich','elmich@gmail.com','0689264789')," +
                "(4,'Toshiba','toshiba@gmail.com','0489528475')");


        sqLiteDatabase.execSQL("INSERT INTO sanpham VALUES " +
                "(1,'Máy lạnh Panasonic Inverter 1 HP CU/CS-PU9AKH-8',11690000,'panasonic_hp','Một chiều chỉ làm lạnh',1)," +
                "(2,'Máy giặt Toshiba 8 kg AW-M905BV(MK)',4990000,'toshiba_mk','Khả năng tiết kiệm điện hiệu quả với công nghệ Inverter kết hợp cảm biến thông minh Econavi, Multi Control',4)," +
                "(3,'Panasonic Inverter 325 lít NR-BC361VGMV',14990000,'panasonic_nr','iPhone 14 Pro 256GB likenew ATV - Vàng',1)," +
                "(4,'NỒI CƠM ĐIỆN TỬ TOSHIBA RC-18NTFV(W)',3050000,'toshiba_rc','Lòng nồi chống dính, chống trầy',4)," +
                "(5,'LÒ NƯỚNG TOSHIBA TL-MC35Z',2000000,'toshiba_tl','Hai dàn nhiệt điều chỉnh độc lập',4)," +
                "(6,'Máy xay sinh tố đa năng Philips HR2041/10 - 2 cối ',630000,'philips_hr','Thiết kế độc đáo, hiện đại, màu sắc nhã nhặn, xay được nhiều loại thực phẩm khác nhau, đáp ứng nhu cầu cơ bản của cá nhân hoặc gia đình',2)," +
                "(7,'Máy xay sinh tố đa năng Panasonic MX-MG5351WRA ',1450000,'philips_hr','Với công suất 700W, lưỡi dao sắc bén, cùng công nghệ đảo trộn V&M, giúp bạn có thể dễ dàng chuẩn bị cho mình các loại sinh tố rau củ, trái cây bổ dưỡng',1)," +
                "(8,'Chảo nhôm chống dính đáy từ 20 cm Elmich Smartcook SM5705MN',151000,'elmich_smmn','Thiết kế đáy phẳng 2 lớp nhôm và inox 430 bắt nhiệt đồng đều, hiệu quả, cho khả năng gia nhiệt nhanh hơn',3)," +
                "(9,'Bộ 3 nồi nhôm chống dính nắp kính Elmich EL-5112YA',990000,'elmich_elya','Lòng nồi được phủ lớp chống dính Dupont Teflon vân đá ánh kim an toàn, bền bỉ ',3)," +
                "(10,'Panasonic Inverter 1.5 HP CU/CS-PU12AKH-8 Mới 2024',14269000,'panasonic_hp','Một chiều chỉ làm lạnh',1)");


        sqLiteDatabase.execSQL("INSERT INTO admin VALUES ('admin','Tuyet Chinh','admin')");


        sqLiteDatabase.execSQL("INSERT INTO khachhang VALUES ('khachhang1','minh tuan','123','0384396100','tiengiang')");


        sqLiteDatabase.execSQL("insert into hoadon values (1,'khachhang1',1,20000000)");

        sqLiteDatabase.execSQL("insert into thongbao values (1,'khach hang da dang nhap','khachhang1')");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("drop table if exists admin");
        sqLiteDatabase.execSQL("drop table if exists khachhang");
        sqLiteDatabase.execSQL("drop table if exists sanpham");
        sqLiteDatabase.execSQL("drop table if exists sanphamgh");
        sqLiteDatabase.execSQL("drop table if exists sanphamyt");
        sqLiteDatabase.execSQL("drop table if exists hoadon");
        //
        sqLiteDatabase.execSQL("drop table if exists chitiethd");
        sqLiteDatabase.execSQL("drop table if exists hoadon1");

        ////
        sqLiteDatabase.execSQL("drop table if exists nhacc");


    }

    public String checkCredentials(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryAdmin = "SELECT * FROM admin WHERE username = ? AND password = ?";
        String queryKhachHang = "SELECT * FROM khachhang WHERE username = ? AND password = ?";

        Cursor cursorAdmin = db.rawQuery(queryAdmin, new String[]{username, password});
        if (cursorAdmin.getCount() > 0) {
            cursorAdmin.close();
            db.close();
            return "admin";
        }

        Cursor cursorKhachHang = db.rawQuery(queryKhachHang, new String[]{username, password});
        if (cursorKhachHang.getCount() > 0) {
            cursorKhachHang.close();
            db.close();
            return "khachhang";
        }

        cursorAdmin.close();
        cursorKhachHang.close();
        db.close();
        return "invalid";
    }


}
