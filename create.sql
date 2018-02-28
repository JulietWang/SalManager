create user guigu identified by "guigu";
grant connect,resource to guigu;
--NLS_LANG
select userenv ('language')from dual

CREATE TABLE category (
  category_id varchar2(32) NOT NULL ,-- '商品种类id',
  name varchar2(32) NOT NULL, --'商品种类名称',
  del_flag number(1) NOT NULL -- '删除标识{1表示已删除，0表示未删除}'
);
alter table category add constraint pk_category_id primary key (category_id);
-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO category VALUES ('a3efa0816fb14a4787c62a5aa6655590', '零食', 0);
INSERT INTO category VALUES ('e509030e84ed4ba9994d7dff4988a020', '饮品',  0);
INSERT INTO category VALUES ('753893c896b045a3a18b0f1804039093', '日用品',  0);
INSERT INTO category VALUES ('4cd7d2c32bd44fdfbb78902ec647ed5d', '文具',  0);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
CREATE TABLE goods(
  goods_id varchar2(32) DEFAULT NULL ,-- '商品id',
  name varchar2(30) DEFAULT NULL,-- '商品名称',
  price number(10,2) DEFAULT NULL,-- '商品价格',
  origin varchar2(30) DEFAULT NULL ,-- '商品产地',
  stock number not null ,--'商品库存',
  warehouse_id varchar2(32) ,-- '商品所属仓库id',
  category_id varchar2(32) ,-- '商品种类id',
  del_flag number(1) not null-- '删除标识{1表示已删除，0表示未删除}'
)
alter table goods add constraint pk_goods_id primary key (goods_id);
-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO goods VALUES ('51245a3e5ef74f749a09dd69a1276e5a', '可比克薯片', '9.00', '哈尔滨', '245', 'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', 0);
INSERT INTO goods VALUES ('12cebd4dda85494099f674641db0d143', '唐僧肉', '3.50', '温州', '133', 'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', 0);
INSERT INTO goods VALUES ('8dc70cb890de407999ad13cd275994f1', '老北京鸡爪', '1.50', '福建', '67', 'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', 0);
INSERT INTO goods VALUES ('098c3ed447234ebbaf2d39a3f59835f4', '优悦矿泉水', '2.00', '哈尔滨', '190', '4d3bde71480e43d599c436ea673eb964', 'e509030e84ed4ba9994d7dff4988a020', 0);
INSERT INTO goods VALUES ('2fa56f90c93e40f6b143e8775205f76a', '康师傅冰红茶（瓶）', '3.00', '长春', '60', '4d3bde71480e43d599c436ea673eb964', 'e509030e84ed4ba9994d7dff4988a020', 0);
INSERT INTO goods VALUES ('ebe99d6a1fc449b7a3ab42cbbb045503', '清扬洗发露', '25.00', '北京', '9', '8b4ea76552764374932ea611468bf1a4', '753893c896b045a3a18b0f1804039093', 0);
INSERT INTO goods VALUES ('5731efbc2799412a93a59169a9ffcd66', '纳爱斯香皂', '6.50', '上海', '100', '498359201b4f4edbb137a6d0ba82d9b1', '753893c896b045a3a18b0f1804039093', 0);
INSERT INTO goods VALUES ('2e70d079865948b89d390c5ac4528cfe', '康师傅红烧牛肉面（袋）', '2.50', '吉林', '1000', 'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', 0);
INSERT INTO goods VALUES ('1bca949698144652a0f34bd6259e6bfe', '快客钢笔', '108.00', '北京', '100', '498359201b4f4edbb137a6d0ba82d9b1', '4cd7d2c32bd44fdfbb78902ec647ed5d', 0);
INSERT INTO goods VALUES ('9cf1700221da4bcd9a8df0a2788a6c14', '作业本', '1.00', '山西', '1000', '8b4ea76552764374932ea611468bf1a4', '4cd7d2c32bd44fdfbb78902ec647ed5d', 0);
INSERT INTO goods VALUES ('bb9fcbd22dc14c7bb35df519cd893165', '陈醋', '25.00', '山西太原', '76', '498359201b4f4edbb137a6d0ba82d9b1', '753893c896b045a3a18b0f1804039093', 0);
INSERT INTO goods VALUES ('a983c1bd6e364a4895b79021584acf6c', '一个鸡蛋雪糕（支）', '1.00', '山东', '2000', '4d3bde71480e43d599c436ea673eb964', 'a3efa0816fb14a4787c62a5aa6655590', 0);
INSERT INTO goods VALUES ('952a64e8dfd6488cb9eb9d21b16169f5', '晨光签字笔（支）', '3.00', '上海', '10000', '8b4ea76552764374932ea611468bf1a4', '4cd7d2c32bd44fdfbb78902ec647ed5d', 0);
INSERT INTO goods VALUES ('fe627e620f804ca8984281da5a3662e7', 'asd', '1123.00', 'asd', '1123', 'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', 1);
-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE users (
  user_id varchar2(32) NOT NULL,
  name varchar2(20) NOT NULL ,-- '用户名',
  password varchar2(20) NOT NULL ,-- '密码',
  identity varchar2(1) NOT NULL-- '身份标识:0代表普通用户，1代表管理员'
);
alter table users add constraint pk_users_id primary key (user_id);
-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO users VALUES ('78d5bc915240430b899527acc55bcf66', 'admin', '123456', '1');
INSERT INTO users VALUES ('2db47a8bc40e4592b7233f7c07453f8b', 'lishuai', '123456', '0');
INSERT INTO users VALUES ('3db47a8bc40e4592b7233f7c07453f8b', 'guigu', 'guigu', '0');
-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
CREATE TABLE warehouse (
  warehouse_id varchar(32) primary key,-- '仓库id',
  name varchar(20) NOT NULL ,-- '仓库名称',
  del_flag number(1) not null,-- '删除标识{1表示已删除，0表示未删除}',
  sort number(10) NOT NULL-- '排序规则',
);
-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO warehouse VALUES ('ad2ee5024fde43feb3a26b81190deba8', '第一仓库', 0, '1');
INSERT INTO warehouse VALUES ('4d3bde71480e43d599c436ea673eb964', '第二仓库', 0, '2');
INSERT INTO warehouse VALUES ('498359201b4f4edbb137a6d0ba82d9b1', '第三仓库', 0, '3');
INSERT INTO warehouse VALUES ('8b4ea76552764374932ea611468bf1a4', '第四仓库', 0, '4');
-- ----------------------------
-- Table structure for customer
-- ----------------------------
create table customer(

      customer_id varchar2(32) not null,--编号 pk fk
      customersimplename varchar(20) not null,--客户公司简称
      customername varchar2(60) not null,--客户公司全称
      owner varchar2(30) not null,--负责人
      mobilephone varchar2(20) not null,--移动电话
      salesman_id varchar2(32),--销售员编号 fk
      cutomeraddress varchar2(100) not null,--客户送货地址
      lastdeliverdate varchar2(10) not null,--最后送货日期  
      del_flag number(1) not null-- '删除标识{1表示已删除，0表示未删除}',
      
);
alter table customer add constraint pk_customer_customerid primary key (customer_id);
-- ----------------------------
-- Table structure for supplier
-- ----------------------------
create table supplier(
       
       supplier_id varchar2(32) not null,--编号 非空pk
       suppliersimplename varchar2(20) not null,--供应商简称 非空
       suppliername varchar2(60) not null,--供应商全称
       owner varchar2(30) not null,--负责人
       mobiletelephone varchar2(20) not null,--移动电话
       companyaddress varchar2(100) not null,--公司地址
       factoryaddress varchar2(100) not null,--工厂地址
       lastpurchasedate varchar2(10) not null,--最后进货日期
       del_flag number(1) not null-- '删除标识{1表示已删除，0表示未删除}',
);
alter table supplier add constraint pk_supplier_supplierid primary key (supplier_id);

-- ----------------------------
-- Table structure for salesman
-- ----------------------------
create table salesman(
       salesman_id varchar2(32) not null ,--编号 非空 pk
       salesmanname varchar2(10) not null,--中文名称 非空
       mobiletelephone varchar2(20) not null,--移动电话
       contactaddress varchar2(50) not null,--联络地址
       email varchar2(30) not null,--电子邮件
       del_flag number(1) NOT NULL -- '删除标识{1表示已删除，0表示未删除}'
);
alter table salesman add constraint pk_salesman_salesmanid primary key (salesman_id);
-- ----------------------------
-- Table structure for sale_order
-- ----------------------------
CREATE TABLE sale_order (
  sale_id varchar2(32) NOT NULL ,-- '订单id',
  bill_no varchar2(20) NOT NULL ,-- '订单号',
  salesman_id varchar2(32) ,-- '销售人员id',salesman_id
  category_id varchar2(32) ,-- '所属分类id',
  warehouse_id varchar2(32) ,-- '所属仓库id',
  amount number NOT NULL,-- '销售数量',
  goods_id varchar(32),--'商品id',
  customer_id varchar2(32),--客户id
  del_flag number(1) NOT NULL -- '删除标识{1表示已删除，0表示未删除}'
)
alter table sale_order add constraint pk_sale_order_id primary key (sale_id);
-- ----------------------------
-- Table structure for stock_order
-- ----------------------------
CREATE TABLE stock_order (
  stock_id varchar2(32) NOT NULL ,-- 'id',
  bill_no varchar2(20) NOT NULL ,-- '订单号',salesmanid
  user_id varchar2(32) ,-- '用户id',
  supplier_id varchar2(32) ,--供应商id
  warehouse_id varchar2(32) ,-- '商品所属仓库id',
  category_id varchar2(32) ,-- '商品所属分类id',
  amount number NOT NULL ,-- '修改数量',
  goods_id varchar2(32) ,-- '商品id',
  sign number(1) NOT NULL ,-- '出入库订单区分标识(0表示入库，1表示出库)',
  del_flag number(1)-- '删除标识{1表示已删除，0表示未删除}'
);
alter table stock_order add constraint pk_stock_order_id primary key (stock_id);
-------------------------------
-- Records of 外键
-- ----------------------------
alter table goods add constraint fk_goods_warehouse foreign key(warehouse_id) references warehouse(warehouse_id);
alter table goods add constraint fk_goods_category foreign key(category_id) references category(category_id);

--select * from salesman
--select * from stock_order
alter table stock_order add constraint fk_stock_order_salesman foreign key(user_id) references users(user_id);
alter table stock_order add constraint fk_stock_order_warehouse foreign key(warehouse_id) references warehouse(warehouse_id);
alter table stock_order add constraint fk_stock_order_category foreign key(category_id) references category(category_id);
alter table stock_order add constraint fk_stock_order_goods foreign key(goods_id) references goods(goods_id);
alter table stock_order add constraint fk_stock_order_supplier foreign key(supplier_id) references supplier(supplier_id);

alter table sale_order add constraint fk_sale_order_salesman foreign key(salesman_id) references salesman(salesman_id);
alter table sale_order add constraint fk_sale_order_customer foreign key(customer_id) references customer(customer_id);
alter table sale_order add constraint fk_sale_order_warehouse foreign key(warehouse_id) references warehouse(warehouse_id);
alter table sale_order add constraint fk_sale_order_category foreign key(category_id) references category(category_id);
alter table sale_order add constraint fk_sale_order_goods foreign key(goods_id) references goods(goods_id);

alter table customer add constraint fk_customer_salesman foreign key(salesman_id) references salesman(salesman_id);
-------------------------------
-- Records of salesman 销售人员表
-- ----------------------------
insert into salesman values('78d5bc915240430b899527acc55bcf66','张三','1351184488','浙江省杭州市华星路99号','1351184488@163.com',0);
insert into salesman values('2db47a8bc40e4592b7233f7c07453f8b','李四','1353387688','浙江省杭州市网商路30号',' 1353387688@163.com',0);
insert into salesman values('2db47a8bc40e4592b7233f7c07453f8c','赵二','1883345678','浙江省杭州市网商路599号',' 1353397688@163.com',0);

-- ----------------------------
-- Records of customer 客户信息表
-- ----------------------------
insert into customer values('8b4ea77552764374932ea611468bf1a4','白度','白度责任有限公司','李彦宏','1882288888','78d5bc915240430b899527acc55bcf66','北京市海淀区西北旺东路10号院百度科技园1号楼－5号楼','2017-11-9',0);
insert into customer values('8b4ea78552764374932ea611468bf1a4','心浪','心浪实业有限公司','王志东','1884488888','2db47a8bc40e4592b7233f7c07453f8b',' 北京市海淀区西北旺东路10号院西区8号楼新浪总部大厦','2017-10-9',0);
insert into customer values('8b4ea79552764374932ea611468bf1a4','疼迅','疼迅实业有限公司','马化腾','1885588888','2db47a8bc40e4592b7233f7c07453f8c','广东省深圳市深南中路2053号','2017-10-7',0);
-- ----------------------------
-- Records of supplier 供应商资料表
-- ----------------------------                           
insert into supplier values('498359201b5f4edbb137a6d0ba82d9b1','阿里旺旺','阿里旺旺责任有限公司','马云','1881188888','浙江省杭州市华星路99号东软创业大厦六层','江苏省宿迁市泗阳县众兴中路9号','2017-8-6',0);
insert into supplier values('498359201b3f4edbb137a6d0ba82d9b1','网意','网意责任有限公司','丁磊','1883388888','浙江省杭州市网商路599号',' 台灣省台北市內湖區新湖二路28號5F','2017-8-10',0);
-- ----------------------------
-- Records of stock_order
-- ----------------------------
INSERT INTO stock_order VALUES ('9432e438aba147e89c0c9ec3270d2b28', '16061503430001', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1', 'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', '100', '51245a3e5ef74f749a09dd69a1276e5a', '0', 0);
INSERT INTO stock_order VALUES ('89bd6713ee1f4f91a0cc0f048df38580', '16061503430002', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1',  'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', '50', '12cebd4dda85494099f674641db0d143', '0', 0);
INSERT INTO stock_order VALUES ('ab85de1de25f47f09a7752cf93005fea', '16061503430003', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1',  '4d3bde71480e43d599c436ea673eb964', 'a3efa0816fb14a4787c62a5aa6655590', '1000', 'a983c1bd6e364a4895b79021584acf6c', '0', 0);
INSERT INTO stock_order VALUES ('203e450b461848daaab762190d22aebf', '16061503430004', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1',  '4d3bde71480e43d599c436ea673eb964', 'e509030e84ed4ba9994d7dff4988a020', '200', '098c3ed447234ebbaf2d39a3f59835f4', '0', 0);
INSERT INTO stock_order VALUES ('fb8144a480b740a791bd772090e828da', '16061504030001', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1',  'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', '50', '51245a3e5ef74f749a09dd69a1276e5a', '1', 1);
INSERT INTO stock_order VALUES ('ab40b685a3244148bd064fc6a1649c7c', '16061508160001', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1', '4d3bde71480e43d599c436ea673eb964', 'e509030e84ed4ba9994d7dff4988a020', '10', '098c3ed447234ebbaf2d39a3f59835f4', '1', 1);
INSERT INTO stock_order VALUES ('dde0fbab4d4842308088f77adea657d0', '16061508170001', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1',  '4d3bde71480e43d599c436ea673eb964', 'e509030e84ed4ba9994d7dff4988a020', '10', '098c3ed447234ebbaf2d39a3f59835f4', '1', 0);
INSERT INTO stock_order VALUES ('ab13d1485c5d408a8d69e4625aed77a3', '16061509520001', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1',  'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', '10', '8dc70cb890de407999ad13cd275994f1', '1', 0);
INSERT INTO stock_order VALUES ('f872620f35fc4097b1cfe8bf8876356b', '16061509530001', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1',  'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', '30', '8dc70cb890de407999ad13cd275994f1', '0', 0);
INSERT INTO stock_order VALUES ('6f45a6a3b5eb463992ad21ec7f97f4d8', '16061511550002', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1','498359201b4f4edbb137a6d0ba82d9b1', '753893c896b045a3a18b0f1804039093', '2', 'bb9fcbd22dc14c7bb35df519cd893165', '1', 0);
INSERT INTO stock_order VALUES ('e45cedc328504f02a90c3fc3494394a7', '16061513010002', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1','ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', '1', '51245a3e5ef74f749a09dd69a1276e5a', '1', 1);
INSERT INTO stock_order VALUES ('d4e8c8d4798441b194e402b3fcd0aced', '16061513430001', '2db47a8bc40e4592b7233f7c07453f8b','498359201b5f4edbb137a6d0ba82d9b1', 'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', '25', '51245a3e5ef74f749a09dd69a1276e5a', '0', 0);
INSERT INTO stock_order VALUES ('12ec02f2f4ce4e8eb096b0d70a3a4474', '16061513440002', '2db47a8bc40e4592b7233f7c07453f8b','498359201b5f4edbb137a6d0ba82d9b1','ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', '30', '51245a3e5ef74f749a09dd69a1276e5a', '1', 0);
INSERT INTO stock_order VALUES ('e41ba45b1af0486f8bd3cce2a211e657', '16061513500001', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1','ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', '5', '8dc70cb890de407999ad13cd275994f1', '0', 1);
INSERT INTO stock_order VALUES ('b41b821cb7cb4db29137bc0564a088a0', '16061513500003', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1', 'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', '3', '8dc70cb890de407999ad13cd275994f1', '1', 0);
INSERT INTO stock_order VALUES ('65f68a9c21c94cb1a6686231d7c6a2fb', '16061513590002', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1', 'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', '5', '12cebd4dda85494099f674641db0d143', '1', 0);
INSERT INTO stock_order VALUES ('b98fd1145c4d449e8edf7ee4b613907a', '16061516440002', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1', 'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', '5', '8dc70cb890de407999ad13cd275994f1', '1', 0);
INSERT INTO stock_order VALUES ('afa98b3937e44e3dab8f48ae232c6996', '16061517480002', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1', 'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', '9', '12cebd4dda85494099f674641db0d143', '1', 0);
INSERT INTO stock_order VALUES ('6b5d610270c0451b8391432de09782f8', '16061517480003', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1', 'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', '1', '12cebd4dda85494099f674641db0d143', '1', 0);
INSERT INTO stock_order VALUES ('23835ecee6a1496b95d825598a8c8818', '16061517490001', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1', 'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', '10', '51245a3e5ef74f749a09dd69a1276e5a', '0', 0);
INSERT INTO stock_order VALUES ('20bfe0a048c648cdbcd03545693dbe62', '16061518070002', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1', 'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', '1', '12cebd4dda85494099f674641db0d143', '1', 0);
INSERT INTO stock_order VALUES ('604a3a7123c2472dba0669f1d169fa37', '16061518070003', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1', 'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', '1', '51245a3e5ef74f749a09dd69a1276e5a', '0', 0);
INSERT INTO stock_order VALUES ('2e688d947b1242158c0a0d0ff36d31fa', '16061518070004', '78d5bc915240430b899527acc55bcf66','498359201b5f4edbb137a6d0ba82d9b1', 'ad2ee5024fde43feb3a26b81190deba8', 'a3efa0816fb14a4787c62a5aa6655590', '1', '12cebd4dda85494099f674641db0d143', '1', 0);
-- ----------------------------
-- Records of sale_order
-- ----------------------------

INSERT INTO sale_order VALUES ('14801b2d0d7f44ddbf39b31ef6fdc506', '16061511410001', '78d5bc915240430b899527acc55bcf66', 'e509030e84ed4ba9994d7dff4988a020', '4d3bde71480e43d599c436ea673eb964', '10', '098c3ed447234ebbaf2d39a3f59835f4','8b4ea77552764374932ea611468bf1a4' ,0);
INSERT INTO sale_order VALUES ('034ae072386f4e619331ee348311fa57', '16061511420001', '78d5bc915240430b899527acc55bcf66', 'e509030e84ed4ba9994d7dff4988a020', '4d3bde71480e43d599c436ea673eb964', '10', '098c3ed447234ebbaf2d39a3f59835f4','8b4ea78552764374932ea611468bf1a4' , 0);
INSERT INTO sale_order VALUES ('48020916678a4078b7d8916ca4d1a9a0', '16061511490001', '78d5bc915240430b899527acc55bcf66', '753893c896b045a3a18b0f1804039093', '498359201b4f4edbb137a6d0ba82d9b1', '2', 'bb9fcbd22dc14c7bb35df519cd893165', '8b4ea77552764374932ea611468bf1a4' ,0);
INSERT INTO sale_order VALUES ('081451903f88435c95992991a8d79ee0', '16061511550001', '2db47a8bc40e4592b7233f7c07453f8c', '753893c896b045a3a18b0f1804039093', '498359201b4f4edbb137a6d0ba82d9b1', '2', 'bb9fcbd22dc14c7bb35df519cd893165', '8b4ea79552764374932ea611468bf1a4' ,0);
INSERT INTO sale_order VALUES ('27922ddf90fe4f85af1f61b9ac998686', '16061513010001', '78d5bc915240430b899527acc55bcf66', 'a3efa0816fb14a4787c62a5aa6655590', 'ad2ee5024fde43feb3a26b81190deba8', '1', '51245a3e5ef74f749a09dd69a1276e5a','8b4ea78552764374932ea611468bf1a4' , 1);
INSERT INTO sale_order VALUES ('8480b09114f84f88ac13af1bb11583a5', '16061513440001', '2db47a8bc40e4592b7233f7c07453f8b', 'a3efa0816fb14a4787c62a5aa6655590', 'ad2ee5024fde43feb3a26b81190deba8', '30', '51245a3e5ef74f749a09dd69a1276e5a','8b4ea77552764374932ea611468bf1a4' , 0);
INSERT INTO sale_order VALUES ('963a340c38d84f2e8e8b3fe55b818823', '16061513500002', '78d5bc915240430b899527acc55bcf66', 'a3efa0816fb14a4787c62a5aa6655590', 'ad2ee5024fde43feb3a26b81190deba8', '3', '8dc70cb890de407999ad13cd275994f1','8b4ea79552764374932ea611468bf1a4' , 0);
INSERT INTO sale_order VALUES ('ad4916d66e3344a7ad7dd968d7acc6c4', '16061513590001', '78d5bc915240430b899527acc55bcf66', 'a3efa0816fb14a4787c62a5aa6655590', 'ad2ee5024fde43feb3a26b81190deba8', '5', '12cebd4dda85494099f674641db0d143','8b4ea77552764374932ea611468bf1a4' , 0);
INSERT INTO sale_order VALUES ('f4ddc233f9344d2cb18c057878f2aafb', '16061516440001', '2db47a8bc40e4592b7233f7c07453f8b', 'a3efa0816fb14a4787c62a5aa6655590', 'ad2ee5024fde43feb3a26b81190deba8', '5', '8dc70cb890de407999ad13cd275994f1','8b4ea78552764374932ea611468bf1a4' , 0);
INSERT INTO sale_order VALUES ('1cc33f4be34c424189fa414664c4a548', '16061517480001', '78d5bc915240430b899527acc55bcf66', 'a3efa0816fb14a4787c62a5aa6655590', 'ad2ee5024fde43feb3a26b81190deba8', '9', '12cebd4dda85494099f674641db0d143','8b4ea79552764374932ea611468bf1a4' , 0);
INSERT INTO sale_order VALUES ('32106640a0c4473b8419156d2d0b26a6', '16061518070001', '78d5bc915240430b899527acc55bcf66', 'a3efa0816fb14a4787c62a5aa6655590', 'ad2ee5024fde43feb3a26b81190deba8', '1', '12cebd4dda85494099f674641db0d143', '8b4ea78552764374932ea611468bf1a4' ,0);


select * from Customer where del_flag=0

select * from Customer
