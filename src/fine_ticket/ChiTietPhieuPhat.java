package fine_ticket;

import java.util.Scanner;

import book.Sach;
import book.SachGiaoKhoa;
import book.SachThamKhao;
import data.SharedData;

public class ChiTietPhieuPhat {
    // ý tưởng:
    private String idPhieuPhat = "PP" + String.format("%03d", DanhSachPhieuPhat.maID);
    private String idSach;
    private int tienPhat;
    private int idDieuKhoan;

    public ChiTietPhieuPhat() {
        idSach = "none";
        tienPhat = 0;
        idDieuKhoan = 0;
    }

    public ChiTietPhieuPhat(String idPhieuPhat, String idSach, int tinhTrangSach, int tienPhat, int idDieuKhoan) {
        this.idPhieuPhat = idPhieuPhat;
    	this.idSach = idSach;
        this.tienPhat = tienPhat;
        this.idDieuKhoan = idDieuKhoan;
    }

    public ChiTietPhieuPhat(ChiTietPhieuPhat A) {
        idSach = A.idSach;
        tienPhat = A.tienPhat;
        idDieuKhoan = A.idDieuKhoan;
    }

    public String getIDChiTietPhieuPhat() {
        return idPhieuPhat;
    }

    public String getIDSach() {
        return idSach;
    }

    public int getTienPhat() {
        return tienPhat;
    }

    public int getIDDieuKhoan() {
        return idDieuKhoan;
    }

    public void setIDChiTietPhieuPhat(String x)
    {
    	idPhieuPhat = x;
    }
    public void setIDSach(String id) {
        idSach = id;
    }

    public void setTienPhat(int x)
    { 
    	tienPhat = x;
    }

    public void setidDieuKhoan(int x) {
        idDieuKhoan = x;
        tienPhat = SharedData.dSDK.dSDK[x].getTienPhat();
        if (tienPhat == 0)
        { 
        	for (Sach i : SharedData.dSS.dSSach)
    		{
    			if (i instanceof SachGiaoKhoa && ((SachGiaoKhoa)i).getIDSach().equals(idSach)) 
    			{ 
    				tienPhat = i.getGia();
    			}
    			else if (i instanceof SachThamKhao && ((SachThamKhao)i).getIDSach().equals(idSach)) 
    			{ 
    				tienPhat = i.getGia();
    			}
    		}
        }
    }

	@Override 
    public String toString() {
        return String.format("%-20s%-15s%-15s%s", idPhieuPhat, idSach, String.format("%,d", tienPhat) + " dong", "Dieu " + idDieuKhoan);
    }
	
	Scanner scan = new Scanner(System.in);
	
	//them chi tiet phieu cung phieu phat
	void them()
	{ 
		System.out.print("Nhap ID Sach: ");
		String ID = scan.nextLine();
		int val = tonTaiIDSach(ID);
		while (val == -1)
		{ 
			System.out.print("Khong ton tai ID Sach da nhap trong Danh Sach Sach, nhap lai (Y/N)? ");
			char ind = scan.next().charAt(0);
			if (!(ind == 'Y' || ind == 'y'))
				return;
			scan.nextLine();
			System.out.print("Nhap ID Sach: ");
			ID = scan.nextLine();
			val = tonTaiIDSach(ID);
		}
		idSach = ID;
		System.out.print("Dieu Khoan vi pham: ");
		idDieuKhoan = scan.nextInt();
		if (SharedData.dSDK.dSDK[idDieuKhoan].getTienPhat() == 0)
		{ 
			tienPhat = val;
		}
		else
		tienPhat = SharedData.dSDK.dSDK[idDieuKhoan].getTienPhat();
	}
	
	//Bo sung chi tiet phieu phat cho phieu phat da ton tai
	public Boolean boSung()
	{
		System.out.print("Bo sung chi tiet phieu cho phieu phat: ");
		int ID = scan.nextInt();
		scan.nextLine();
		while (!tonTaiID(ID))
		{
			System.out.print("Khong tim thay ID Phieu Phat de bo sung, nhap lai (Y/N)? ");
			char in = scan.next().charAt(0);
			if (in == 'Y' || in == 'y')
				ID = scan.nextInt();
			else
				return false;
		}
		
		idPhieuPhat = String.format("PP%03d", ID);
		them();
		return true;
	}
	
	int tonTaiIDSach(String ID)
	{ 
		for (Sach i : SharedData.dSS.dSSach)
		{
			if (i instanceof SachGiaoKhoa && ((SachGiaoKhoa)i).getIDSach().equals(ID)) 
			{ 
				return i.getGia();
			}
			else if (i instanceof SachThamKhao && ((SachThamKhao)i).getIDSach().equals(ID)) 
			{ 
				return i.getGia();
			}
		}
		return -1;
	}
	
	private Boolean tonTaiID(int ID)
	{ 
		int exist = 0;
		for (PhieuPhat i : SharedData.dSPP.dSPP)
		{ 
			if (i.getIDPhieuPhat().equals(String.format("PP%03d", ID)))
			{ 
				++exist;
			}
		}
		if (exist == 0)
			return false;
		else 
			return true;
	}
}
