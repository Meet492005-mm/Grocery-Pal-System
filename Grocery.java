import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;
class Store 
{
    int Product_id;
    String ProductName;
    int ProductStock;
    int ProductPrice;
    int Customerid;
    String CustomerName;
    String Customercontactno;
    
}

class Product extends Store
{
    
    int Product_id;
    String ProductName;
    int ProductStock;
    int ProductPrice;
   
      public Product(int product_id, String productName, int productStock, int productPrice) {
      Product_id = product_id;
      ProductName = productName;
      ProductStock = productStock;
      ProductPrice = productPrice;
   }


      public int getProduct_id() {
         return Product_id;
      }



      public String getProductName() {
         return ProductName;
      }



      public int getProductStock() {
         return ProductStock;
      }



      public int getProductPrice() {
         return ProductPrice;
      }


      public void setProduct_id(int product_id) {
         Product_id = product_id;
      }


      public void setProductName(String productName) {
         ProductName = productName;
      }


      public void setProductStock(int productStock) {
         ProductStock = productStock;
      }


      public void setProductPrice(int productPrice) {
         ProductPrice = productPrice;
      }
      void change()
      {
         int m=10;
         String d;
         d=m+"";
      }

      @Override
      public String toString() {
         return "Product [Product_id=" + Product_id + ", ProductName=" + ProductName + ", ProductStock=" + ProductStock
               + ", ProductPrice=" + ProductPrice + "]";
      }


      ArrayList<Product> groceries = new ArrayList<>();
      Scanner sc = new Scanner(System.in);       
  
     public void AddProduct(Connection con) throws Exception
      {
             Scanner sc = new Scanner(System.in);
             System.out.println("Enter Product Name you want to add ");
             String ProductName=sc.nextLine();
             try
             {
                String s7 ="Select * from  GroceryProduct  where ProductName=?";
                PreparedStatement pst= con.prepareStatement(s7);
                pst.setString(1, ProductName);
                ResultSet rs = pst.executeQuery();
                if(rs.next())
                {
                   System.out.println("Product Name " + ProductName + "   exists in the database."); 
                   System.out.println("Cannot enter duplicate Product name enter other name:");  
                }  
                else
                {
                   System.out.println("Product Name---->  " + ProductName);
                              System.out.println("Enter ProductStock");
                              ProductStock=sc.nextInt();
                              System.out.println("Enter ProductPrice");
                              ProductPrice=sc.nextInt();
                              Product p1 = new Product(Product_id, ProductName, ProductStock, ProductPrice);
                               groceries.add(p1);
                               System.out.println(p1);
                               System.out.println("Product added Successfully:");
                               String s1 = "insert into GroceryProduct (ProductName,ProductStock,ProductPrice) values (?,?,?)";
                               PreparedStatement pst1 = con.prepareStatement(s1);
                               pst1.setString(1, ProductName);                
                               pst1.setInt(2, ProductStock);
                               pst1.setInt(3,ProductPrice);
                               int r1 = pst1.executeUpdate();
                               if(r1>0)
                               {
                                System.out.println("Product Successfully Added in the Product List:  "+ r1);
                                System.out.println("product_id= "+Product_id+" | "+"name= "+ProductName+" | "+"price= "+ProductPrice+" | "+"stock= "+ProductStock+"  <---Added");
                               }
                               else 
                               {
                                System.out.println("Product Not Added in the Product List:  "+ r1);
                              }   
                }   
             }
             catch( Exception e)
             {
                System.out.println("Error occur while checking the customer_id   "+e.getMessage());
             }    
        }

 

     public void UpdateProduct(Connection con) throws Exception
     {
        System.out.println("Enter product Name   you want to update  in the product list");
        String  productname = sc.nextLine();
          try
          {
             String s2 ="Select * from  GroceryProduct where ProductName=?";
             PreparedStatement pst= con.prepareStatement(s2);
             pst.setString(1, productname);
             ResultSet rs = pst.executeQuery();
             if(rs.next())
             {
                             System.out.println("Product Name " + productname + " exists in the database.");
                             String s3 = "Update  GroceryProduct set ProductStock=?,ProductPrice=? where ProductName=?";
                             PreparedStatement pst1 = con.prepareStatement(s3);
                             pst1.setString(3, productname);
                             System.out.println("Enter Product Stock to Update:");
                             int newstock = sc.nextInt();
                             pst1.setInt(1, newstock);
                             System.out.println("Enter Product Price to Update:");
                             sc.nextLine();
                             int newprice=sc.nextInt();
                             pst1.setInt(2, newprice);
                             int r2 = pst1.executeUpdate();
                             if(r2>0)
                             {
                                 System.out.println("Updation of the Particular Product done in the Product List:"+ r2);
                                 System.out.println("product name= "+productname+" |  "+"newprice= "+newstock+" | "+"newstock= "+newprice+"  <---Updated");
                             }
                            else
                             {
                                 System.out.println("Updation of the Particular Product  not done in the Product List:");
                             }
             }  
             else
             {
                System.out.println("Product Name" + productname + "  does not exists in the database.");
             }   
          }
          catch( Exception e)
          {
             System.out.println("Error occur while checking the product_id"+e.getMessage());
          }
     }

     public void DeleteProduct(Connection con) throws Exception
     {
        System.out.println("Enter productname you want to delete");
        String productname = sc.nextLine();
          try
          {
             String s4 ="Select * from  GroceryProduct where ProductName=?";
             PreparedStatement pst= con.prepareStatement(s4);
             pst.setString(1, productname);
             ResultSet rs = pst.executeQuery();
             if(rs.next())
             {
                System.out.println("Product ID " + productname + " exists in the database.");
                String s5 =" delete from GroceryProduct where ProductName =?";
                PreparedStatement pst1 = con.prepareStatement(s5);
                pst1.setString(1, productname);
                 int r3=pst1.executeUpdate();
                 if(r3>0)
                 {
                  System.out.println("Deleteion of that Particular product done from product list:---->"+ r3);
                  System.out.println("delete_id= "+productname+"  <----Deleted");
                 }
                 else
                 {
                  System.out.println("Deleteion of that Particular product not done from product list---->:"+ r3);
                 }            
             }  
             else
             {
                System.out.println("Product ID " + productname + "  does not exists in the database.");
             }   
          }
          catch( Exception e)
          {
             System.out.println("Error occur while checking the product_id"+e.getMessage());
          }
     }
    
    public void DisplayProduct(Connection con) throws Exception
     {
             String s6 = "Select * from GroceryProduct ";
             Statement st = con.createStatement();
              ResultSet r4 = st.executeQuery(s6);
              System.out.println("List of  all particular product in the list");
              while(r4.next())
              {
                int pid=r4.getInt("Product_id");
                String cname =r4.getString("ProductName");
                int ps=r4.getInt("ProductStock");
                int pp=r4.getInt("ProductPrice");
                Product newproduct = new Product(pid, cname, ps, pp);
                groceries.add(newproduct);
                System.out.println(newproduct);
                System.out.println("-----------------");
              }
    }
  

    public void Checktheproductid(Connection con) throws Exception
      {
        System.out.println("Enter product name to check in list");
        String productname = sc.nextLine();
          try
          {
             String s7 ="Select * from  GroceryProduct where ProductName=?";
             PreparedStatement pst= con.prepareStatement(s7);
             pst.setString(1, productname);
             ResultSet rs = pst.executeQuery();
             if(rs.next())
             {
                System.out.println("Product ID " + productname + " exists in the database.");
             }  
             else
             {
                System.out.println("Product ID " + productname + "  does not exists in the database.");
             }   
          }
          catch( Exception e)
          {
             System.out.println("Error occur while checking the product_id"+e.getMessage());
          }    
      }
}
class Customer extends Store 
{
    int Customerid;
    String CustomerName;
    String Customercontactno;
    public Customer(int customerid, String customerName, String customercontactno) 
    {
        Customerid = customerid;
        CustomerName = customerName;
        Customercontactno = customercontactno;
    }
   
    public int getCustomerid()
    {
        return Customerid;
    }
    public String getCustomerName() 
    {
        return CustomerName;
    }
    public String getCustomercontactno() 
    {
        return Customercontactno;
    }
    public void setCustomerid(int customerid) 
    {
        Customerid = customerid;
    }
    public void setCustomerName(String customerName) 
    {
        CustomerName = customerName;
    }
    public void setCustomercontactno(String customercontactno) 
    {
        Customercontactno = customercontactno;
    }
    public String toString() 
    {
        return "Customer [Customerid=" + Customerid + ", CustomerName=" + CustomerName + ", Customercontactno="
                + Customercontactno + "]";
    }
    //Stack 
      Stack<Customer> customer = new Stack<>();
      public void AddCustomer(Connection con) throws Exception
      {
             Scanner sc = new Scanner(System.in);
             System.out.println("Enter Customer id");
             int Customerid = sc.nextInt();
             sc.nextLine();
             try
             {
                String s7 ="Select * from  GroceryCustomer where Customerid=?";
                PreparedStatement pst= con.prepareStatement(s7);
                pst.setInt(1, Customerid);
                ResultSet rs = pst.executeQuery();
                if(rs.next())
                {
                   System.out.println("Product ID " + Customerid + "   exists in the database."); 
                   System.out.println("Cannot enter duplicate id enter other id:");  
                }  
                else
                {
                   System.out.println("Customer ID---->  " + Customerid );
                   System.out.println("Enter Customer Name:");
                   String CustomerName = sc.nextLine(); 
                   System.out.println("Enter Customer contact no:");
                   String Customercontactno = sc.nextLine();
                   while(true)
                  {
                     if(Customercontactno.length()==10)
                     {
                        System.out.println("phone number is valid");
                        
                     }
                     else
                     {
                        System.out.println("Phone number is not valid");
                        break;
                     }   
                      Customer c = new Customer(Customerid, CustomerName,Customercontactno);
                      customer.push(c);
                      System.out.println(c);
                      String s8 = "insert into GroceryCustomer(Customerid,CustomerName,Customercontactno) values (?,?,?)";
                      PreparedStatement pst5 = con.prepareStatement(s8);
                      pst5.setInt(1, Customerid);
                      pst5.setString(2,CustomerName);
                      pst5.setString(3, Customercontactno);
                      int r =pst5.executeUpdate();
                      if(r>0)
                     {
                           System.out.println("Customer Successfully Added in the Customer List:---->  "+ r);
                            System.out.println("customerid= "+Customerid+" | "+"customername= "+CustomerName+" | "+"customerphone= "+Customercontactno+" |   <---Added");
                            break;
                     }
                     else
                     {
                          System.out.println("Customer Not Added in the Customer List:---->  "+ r);
                     }
                  }   
                   
                }   
             }
             catch( Exception e)
             {
                System.out.println("Error occur while checking the customer_id   "+e.getMessage());
             }    
        }

        public void DisplayCustomer(Connection con) throws Exception
        {
         try
         {
         String s11 = "Select * from GroceryCustomer";
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(s11);
         while (rs.next()) 
         {
              int customer_id =rs.getInt("Customerid");
              String customername=rs.getString("CustomerName");
              String customercontactno=rs.getString("Customercontactno");
              Customer newCustomer = new Customer(customer_id, customername, customercontactno);
              customer.push(newCustomer);
              System.out.println(newCustomer);
         }
         }
         catch(Exception e)
         {
           System.out.println(e.getMessage());
         }
        }
    public void DeleteCustomer(Connection con) throws Exception
      {
      Scanner sc = new Scanner(System.in);
         System.out.println("Enter customer_id you want to delete");
         int customer_id= sc.nextInt();
           try
          {
              String s9 ="Select * from  GroceryCustomer where Customerid=?";
              PreparedStatement pst= con.prepareStatement(s9);
              pst.setInt(1,customer_id);
              ResultSet rs = pst.executeQuery();
              if(rs.next())
              {
                System.out.println(" Customer ID " + customer_id + " exists in the database.");
                while(!customer.isEmpty())
                {
                  Customer c = customer.pop();
                 if(c.getCustomerid()==customer_id)
                    {
                       System.out.println("Deletion Done "+customer_id);
                    }
                    else
                    {
                       System.out.println("customer not deleted ");
                       break;
                    }
                }
                 String s10 =" delete from GroceryCustomer where Customerid = ?";       
                 PreparedStatement pst1 = con.prepareStatement(s10);
                 pst1.setInt(1, customer_id);
                  int r3=pst1.executeUpdate();
                  if(r3>0)
                  {
                   System.out.println("Deleteion of that Particular customer done from customer list:---->"+ r3);
                   System.out.println("delete_id= "+customer_id+"  <----Deleted");
                   customer.pop();
                  }
                 else
                  {
                   System.out.println("Deleteion of that Particular customer not done from customer list---->:"+ r3);
                  }            
              }  
              else
              {
                 System.out.println("Customer ID " + customer_id + "  does not exists in the database.");
              }   
           }
           catch( Exception e)
          {
              System.out.println("Error occur while checking the Customer id"+e.getMessage());
           }
    }
}
class GroceryCart
{
    void Cart(Connection con) throws Exception
    {
        String s4 = "Select * from GroceryProduct ";
        Statement st = con.createStatement();
         ResultSet r4 = st.executeQuery(s4);
         System.out.println("List of  all particular product in the list");
         while(r4.next())
         {
           System.out.println("Product_id:= "+r4.getInt("Product_id"));
           System.out.println("ProductName=  "+r4.getString("ProductName"));
           System.out.println("ProductStock= "+r4.getInt("ProductStock"));
           System.out.println("ProductPrice= "+r4.getInt("ProductPrice"));
           System.out.println("------------------------------------");
         }
    }
    class node
     {
         String data;
         node next;
         node (String data)
         {
             this.data=data;
            this.next=null;
         }
     }
     node first=null;
     public void Customer(Connection con) throws Exception
     {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Customer_id to add");
        int Customer_id = sc.nextInt();
        sc.nextLine();
          try
          {
             String s7 ="Select * from  GroceryCustomer where Customerid=?";
            PreparedStatement pst= con.prepareStatement(s7);
             pst.setInt(1, Customer_id);
             ResultSet rs1 = pst.executeQuery();
             if(rs1.next())
             {
                System.out.println("Customer ID " + Customer_id + " exists in the database.");
                System.out.println("Enter Customer Name:");
                String  CustomerName= sc.nextLine(); 
                String s9 = "select * from GroceryCustomer where customerid = '"+ Customer_id+"' and customername = '"+ CustomerName+"'";
                PreparedStatement pst1 = con.prepareStatement(s9);
               ResultSet rs11= pst1.executeQuery(s9);
               System.out.println("customer id= "+ Customer_id);
               
               System.out.println("customer name= "+ CustomerName);
               Product(con, Customer_id,  CustomerName);  
             }  
             else
             {
                System.out.println("Customer ID " + Customer_id + "  does not exists in the database.");
             }   
          }
          catch( Exception e)
          {
             System.out.println("Error occur while checking the customer_id  "+e.getMessage());
          }
     }
     public void Product(Connection con ,int Customer_id,String CustomerName) throws Exception
     {
         Scanner sc = new Scanner(System.in);
         System.out.println("Enter productname you want to add in cart:");
         String productname=sc.nextLine();
         try
         {
            String s7 ="Select * from  GroceryProduct where ProductName=?";
            PreparedStatement pst= con.prepareStatement(s7);
            pst.setString(1, productname);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
               System.out.println("Product ID " + productname + " exists in the database.");
               System.out.println("Enter quantity of the product:");
               int quant=sc.nextInt();
               Statement MRP = con.createStatement();
               String DRS = "select ProductStock  from GroceryProduct where ProductName= '"+productname+"'";
               ResultSet DMS = MRP.executeQuery(DRS);
               DMS.next();
               int StockAvailable = DMS.getInt(1);
               int Stock =StockAvailable-quant;
               System.out.println("Total Stock=   "+StockAvailable);
               System.out.println("-------------------------");
               node n = new node(productname);
      
                   // Method to check valid product id....
                  String s12 = "select * from GroceryProduct where ProductName = '"+productname+"'";
                  Statement st = con.createStatement();
                  ResultSet rs1 = st.executeQuery(s12);
                   if(rs1.next() && Stock>0)
               {
      
                  //Linkedlist
             if(first==null)
                 {
                   first=n;         
                  }
               else
               {  
                   node temp=first;
                   while(temp.next!=null)
                   {
                        temp=temp.next;                
                   }
                   temp.next=n;
               }
               if(first==null)
               {
                   System.out.println("Empty List:");
               }
               else
               {
                   node temp=first;
                   do
                   {
                       System.out.println("purchased product id =  "+ temp.data);
                       System.out.println("----------------------------");
                       temp=temp.next;
                   }while(temp!=null);
                               String s10="insert into GroceryCart(Customerid,CustomerName,ProductName,Quantity) values(?,?,?,?)";
                               PreparedStatement pst1 = con.prepareStatement(s10);
                               pst1.setInt(1,Customer_id);
                               pst1.setString(2, CustomerName);
                               pst1.setString(3,productname);
                               pst1.setInt(4,quant);
                               pst1.executeUpdate();
                               sc.nextLine();
                               System.out.println("Customer Name= "+CustomerName);
                                System.out.println("--------------------------");
                                System.out.println("Quantity= "+quant);
                                System.out.println("--------------------------");
             }
              if(first!=null)
              { 
                   node temp=first;
                   do
                   {
                       if(Stock>0)
                       {
                           String sql4 = "update GroceryProduct set ProductStock = '"+Stock+"' where ProductName='"+productname+"'";
                           Statement st4 =con.createStatement();
                           st4.executeUpdate(sql4);
                           System.out.println("Stock Left= "+Stock);
                           System.out.println("--------------------------");
                           Statement st2 = con.createStatement();
                           String sql2 = "select * from GroceryCart where ProductName='"+productname+"' and Customerid ='"+Customer_id+"' and customername ='"+CustomerName+"'";
                           System.out.println("Customer ID= "+Customer_id);
                          System.out.println("------------------------------");
                           Statement st3 = con.createStatement();
                           String sql3 = "select * from GroceryProduct where ProductName= '"+productname+"'";
                           ResultSet rs3 = st3.executeQuery(sql3);
                           ResultSet rs2 = st2.executeQuery(sql2);
                           rs2.next();
                           while (rs3.next())
                        {
                               System.out.println("Product Purchased ID  =  "+rs3.getInt("Product_id")); 
                                       System.out.println("------------------------------");
                               System.out.println("Product Purchased Name  =  "+rs3.getString("ProductName"));
                                       System.out.println("------------------------------"); 
                               System.out.println("Product Purchased Price  =  "+rs3.getInt("ProductPrice"));
                                       System.out.println("------------------------------"); 
                       }	
                           temp=temp.next;
                        }
                         else
                          {
                            System.out.println("ERROR");
                          }
      
                     }while(temp!=null);
             }
             }
               else 
                {

                   System.out.println("Quantity is Invald Try Again");
                   Product(con, Customer_id, CustomerName);
                }  
                  Bill(con, Customer_id, CustomerName);
            }
            else
            {
               System.out.println("Product Name " + productname + "  does not exists in the database.");
            }
            }
            catch( Exception e)
         {
            System.out.println("Error occur while checking the product_name"+e.getMessage());
         }   
       }
      public void Bill(Connection con , int Customer_id, String CustomerName ) throws Exception
    {
         Scanner sc= new Scanner(System.in);
          int BillAmount=0;
          System.out.println(" Are you Sure Want to Generate Your Bill");
          System.out.println("Type yes to generate or no to do Shopping Again");
          String ch = sc.nextLine();
          if (ch.equalsIgnoreCase("yes"))
 			{
 				String sql = "select * from GroceryCart where Customerid = '"+Customer_id+"' and CustomerName = '"+CustomerName+"'";
 				Statement st = con.createStatement();
 				ResultSet rs = st.executeQuery(sql);
 				while (rs.next()) {
 					String productname = rs.getString("ProductName");
 					int quant = rs.getInt("quantity");
 					String sql1 = "select * from GroceryProduct where ProductName = '"+productname+"' ";
 					Statement st1 = con.createStatement();
 					ResultSet rs1 = st1.executeQuery(sql1);
 					while (rs1.next())
 					{
 						int StockPrice = rs1.getInt("ProductPrice");
 						int Price = StockPrice*quant;
 						BillAmount = BillAmount + Price;
                         System.out.println("Your Selected Product Cart:");
                         System.out.println("Product_id= "+rs1.getInt(1));
                         System.out.println("ProductName= "+rs1.getString(2));
                         System.out.println("ProductPrice= "+rs1.getInt(4));
                         System.out.println("Total Amount=  "+Price);
                         System.out.println("Your Bill");
                         System.out.println("*******************");
       						 System.out.println("Product_id= "+rs1.getInt(1));
                         System.out.println("ProductName= "+rs1.getString(2));
                         System.out.println("ProductStock=  "+rs1.getInt(3));
                         System.out.println("ProductPrice= "+rs1.getInt(4));
                         System.out.println("Total Amount=  "+Price);
                         System.out.println("********************");
                       	System.out.println();
 					}
				}				
 			}
 			else if(ch.equalsIgnoreCase("no"))
 			{
 				System.out.println("Thank You");
 				String sql2="truncate table grocerycart";
 				Statement st2 = con.createStatement();
 				st2.execute(sql2);
 				Customer(con);
 				return;
 			}
 			else 
 			{
 				System.out.println("Invalid Choice Try Again ");
 				Bill(con, Customer_id, CustomerName);
 			}

     }

}
 class Cart
 {
    int Cart_id;
    String Customer_id;
    String CustomerName;
    int Product_id;
    int Quantity;
    public Cart(int cart_id, String customer_id, String customerName, int product_id, int quantity) 
    {
       Cart_id = cart_id;
       Customer_id = customer_id;
       CustomerName = customerName;
       Product_id = product_id;
       Quantity = quantity;
    }
    public int getCart_id() 
    {
       return Cart_id;
    }
    public String getCustomer_id() 
    {
       return Customer_id;
    }
    public String getCustomerName() 
    {
       return CustomerName;
    }
    public int getProduct_id() 
    {
       return Product_id;
    }
    public int getQuantity() 
    {
       return Quantity;
    }
    public void setCart_id(int cart_id) 
    {
       Cart_id = cart_id;
    }
    public void setCustomer_id(String customer_id) 
    {
       Customer_id = customer_id;
    }
    public void setCustomerName(String customerName) 
    {
       CustomerName = customerName;
    }
    public void setProduct_id(int product_id) 
    {
       Product_id = product_id;
    }
    public void setQuantity(int quantity) 
    {
       Quantity = quantity;
    }
    public String toString() 
    {
      return "Cart [Cart_id=" + Cart_id + ", Customer_id=" + Customer_id + ", CustomerName=" + CustomerName
             + ", Product_id=" + Product_id + ", Quantity=" + Quantity + "]";
    }
    String Filename="Cart.txt";
    public void DisplayCart(Connection con) throws Exception
    {
                     Statement st = con.createStatement();
                     File f = new File(Filename);
                     BufferedWriter sp = new BufferedWriter(new FileWriter(f));
                     String s11 = "Select * from GroceryCart";
                     ResultSet r11 = st.executeQuery(s11);
                     while(r11.next())
                     {
                        int Cart_id = r11.getInt(1);
                        int Customerid=r11.getInt(2);
                        String CustomerName=r11.getString(3);
                        String ProductName=r11.getString(4);
                        int Quantity=r11.getInt(5);
                        sp.write("Cart_id=  "+Cart_id);
                        System.out.println("-----------------------------");
                        sp.newLine();
                        sp.write("Customerid= "+Customerid);
                        System.out.println("------------------------------");
                        sp.newLine();
                        sp.write("CustomerName= "+CustomerName);
                        System.out.println("--------------------------------");
                        sp.newLine();
                        sp.write("ProductName= "+ProductName);
                        System.out.println("------------------------------------");
                        sp.newLine();
                        sp.write("Quantity= "+Quantity);
                        System.out.println("----------------------------------------");
                        sp.newLine();
                        System.out.println("***************************************");
                     } 
                     sp.close();                                  
    }
    public void DisplayCartFile(Connection con) throws Exception
    {
      System.out.println("***************************");
      System.out.println(" Grocery Cart Details:");
      System.out.println("***************************");
        BufferedReader br = new BufferedReader(new FileReader(Filename));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }
 }   
class Grocery
{
    public static void main(String args[]) throws Exception
           {
               String dburl = "jdbc:mysql://localhost:4306/grocery";
               String dbuser = "root";
               String dbpass = "";
               String driver="com.mysql.jdbc.Driver";
               Class.forName(driver);
              Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
              if(con!=null)
              {
                System.out.println("Connection with xammp is successfull:");
              }
              else
              {
                 System.out.println("Connection with xammp is unsuccessfull:");
              } 
              int choice=0;
             do
             {
                Scanner sc = new Scanner(System.in);
                System.out.println("***************************************");
                System.out.println("WELCOME TO GROCERY SHOP");
                System.out.println("***************************************");
                boolean m =true;
                while(m)
                {
                  System.out.println("Press 1 For Owner");
                  System.out.println("Press 2 For Customer");
                  System.out.println("Press 3 For Exit");
                  System.out.println("Enter your choice");
                  int r =0;
                  boolean flag=true;
                  
                        while(flag){
                              try {
                                 r=sc.nextInt();
                                 flag=false;
                                 
                              } catch (Exception e) {
                                 System.out.println("input miss match!");
                                 sc.nextLine();
                        
                              }
                        }
                  switch(r)
                  {
                     case 1:
                     boolean r1=true;
                     while(r1)
                     {
                        System.out.println("Press 1 TO ADD PRODUCT");
                        System.out.println("***************************************");
                        System.out.println("Press 2 TO UPDATE PRODUCT");
                        System.out.println("***************************************");
                        System.out.println("press 3 TO DELETE PRODUCT");
                        System.out.println("***************************************");
                        System.out.println("Press 4 TO DISPLAY ALL PRODUCT");
                        System.out.println("***************************************");
                        System.out.println("Press 5 TO CHECK PRODUCT IN THE LIST");
                        System.out.println("***************************************");  
                        System.out.println("Press 6 TO DISPLAY ALL CUSTOMER");
                        System.out.println("***************************************");
                        System.out.println("Press 7 TO DISPLAY CART");
                        System.out.println("***************************************");
                        System.out.println(" Press 8 To Exit ");
                        System.out.println("******************************************");
                        System.out.println("Enter your Choice");
                       choice=sc.nextInt();
                       if(choice>=1&&choice<=8)
                       {
                       switch(choice)
                     {
                       case 1:
                       Product p1 = new Product(choice, driver, choice, choice);
                       p1.AddProduct(con);
                       break;
                      case 2:
                      Product p2 = new Product(choice, driver, choice, choice);
                      p2.UpdateProduct(con);
                      break;
                      case 3:
                      Product p3 = new Product(choice, driver, choice, choice);
                      p3.DeleteProduct(con);
                     break;
                     case 4:
                     Product p4 = new Product(choice, driver, choice, choice);
                     p4.DisplayProduct(con);
                     break;
                     case 5:
                     Product p5 = new Product(choice, driver, choice, choice);
                     p5.Checktheproductid(con);
                     break;
                     case 6:
                     Customer c3 = new Customer(choice, driver, null);
                     c3.DisplayCustomer(con);
                     break;
                      case 7:
                      Cart c = new Cart(choice, dbpass, driver, choice, choice);
                      c.DisplayCartFile(con);
                      break;
                      case 8:
                      System.out.println("Thank You");
                      r1=false;
                      break;
                     }
                    }
                    else
                    {
                     System.out.println("Invalid Entry: Try Again");
                    }
                  }
               
               
                 break;
                 case 2:
                  boolean r2=true;
                  while(r2)
                  {
                     System.out.println("Press 9 TO ADD CUSTOMER");
                     System.out.println("***************************************");
                     System.out.println("Press 10 To DELETE CUSTOMER");
                     System.out.println("***************************************");
                     System.out.println("Press 11 TO ADD PRODUCT IN THE SHOPPING CART");
                     System.out.println("***************************************");
                     System.out.println(" Press 12 To Exit ");
                     System.out.println("******************************************");
                     System.out.println("Enter your Choice");
                    choice=sc.nextInt();
                    if(choice>=9 &&choice<=12)
                    {
                   switch(choice)
                    {
                     case 9:
                    Customer c1 = new Customer(choice, driver, null);
                    c1.AddCustomer(con);
                    break;
                    case 10:
                    Customer c2 = new Customer(choice, dbpass, driver);
                    c2.DeleteCustomer(con);
                    break;
                    case 11:
                    GroceryCart gc= new GroceryCart();
                    gc.Cart(con);
                    gc.Customer(con);
                    break;
                    case 12:
                    System.out.println("Thakyou");
                    r2=false;
                    break;
                    }
                  }
                  else
                  {
                     System.out.println("Invalid Entry Try Again");
                  }
                  
               }
                  break;
                  case 3:
                  System.out.println("**********************Thank You Visit Again**********************");
                 m=false;
                 sc.close();
                 break;
                 default:
                 System.out.println("Invlaid Chocie");
                 System.out.println("Try Again");
               }
                }
                break;
               }while(choice!=3);
               
            }
    }
    
