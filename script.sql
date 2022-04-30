create database QuanLyPhongKham
go

use QuanLyPhongKham
go

create table Thuoc
(
    maThuoc     varchar(100) primary key,
    tenThuoc    nvarchar(255) not null,
    nuocSanXuat nvarchar(255) not null,
    giaTien     float         not null,
    hanSuDung   datetime      not null,
    moTa        text          not null
)
go

create table BenhNhan
(
    maBenhNhan  varchar(100) primary key,
    tenBenhNhan nvarchar(255) not null,
    tuoi        int           not null,
    gioiTinh    int           not null,
    diaChi      text          not null
)
go

create table DonThuoc
(
    stt               int primary key identity (1, 1),
    maBenhNhan        varchar(100)  not null,
    benhDuocChuanDoan nvarchar(255) not null,
    ngayKham          datetime      not null,
    constraint FK_PRESCRIPTION_PATIENT foreign key (maBenhNhan) references BenhNhan (maBenhNhan)
)
go

create table DonThuocChiTiet
(
    id       int identity (1,1) primary key,
    maThuoc  varchar(100)  not null,
    lieuDung nvarchar(255) not null,
    stt      int           not null,
    constraint FK_DonThuocChiTiet_Thuoc foreign key (maThuoc) references Thuoc (maThuoc),
    constraint FK_DETAIL_PRESCRIPTION foreign key (stt) references DonThuoc (stt)
)