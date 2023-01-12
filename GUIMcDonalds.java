/*
 * Sherry Feng
 * Janruary 8th, 2020
 * Com Sci ISP

notes: the password is: 123
the sales update only account for the changes during one run
for n
 */

package guimcdonalds;

//importing important packages 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

//mcDonalds shop
public class GUIMcDonalds extends JFrame implements ActionListener{
    //declaring frame
    static GUIMcDonalds frame1;
    
    //welcome panel
    static JPanel welcomePan;
    static JLabel displayModeInstructions = new JLabel("Welcome to McDonalds!");//label for instructions
    static JButton customerMode = new JButton("Customer");//button to go to customer options
    static JButton managerMode = new JButton("Manager");//button to go to manager options
    static JButton exitMode = new JButton("Exit Program");//exit and close the window

    //password panel
    static JPanel passwordPan;
    static JLabel pswdInstructions = new JLabel("Please enter the password: ");//prompt to enter password
    static JPasswordField passwordField = new JPasswordField("",10);//text field to enter password
    static JButton enterPassword = new JButton("Enter");//submit password
    static JLabel errorMsg = new JLabel("");//error message if wrong password
    static JButton exitPassword = new JButton("Go Back");//button to go back to the welcome menu

    //manager panel
    static JPanel managerPan;
    
    //text field arrays for quantities, prices and products in the manager panel, editable
    static JTextField[] quantityList = new JTextField[10];
    static JTextField[] priceList = new JTextField[10];
    static JTextField[] productList = new JTextField[10];
    
    //buttons on the panel 
    static JButton saveInventory = new JButton("Save");//save the changes made to inventory
    static JButton exitInventory = new JButton("Close");//go back to the welcome menu
    static JButton goCounters = new JButton("Store Update");//shows the customer counter and total revenue
    
    //labels to show products, prices and the stock, respectively
    static JLabel products = new JLabel("Products");
    static JLabel prices = new JLabel("Prices ($)");
    static JLabel quantities = new JLabel("Stock"); 

    //counter panel
    static JPanel counterPan;
    static JLabel revenueLabel = new JLabel();//displays total revenue made
    static JLabel customerLabel = new JLabel();//number of customers 
    static JButton exitCounters = new JButton("Close");//close the shop update panel

    //customer panel
    static JPanel customerPan;
    
    //label arrays for prices and products, unchangable
    static JLabel cProductList[] = new JLabel[10];
    static JLabel cPriceList[] = new JLabel[10];
    static JTextField cQuantityList[] = new JTextField[10];//texfield arrays for quantities in the cart
    
    static JLabel cartInstructions = new JLabel();//instructions for adding items to cart
    static JLabel cartNum = new JLabel("Enter Quantity: ");//instructions for adding items to cart
    
    static JButton goCheckout = new JButton("Proceed to Checkout");//button to continue to finish checkout
    static JButton cExitBack = new JButton("Close");//button to go back to welcome panel
    
    //checkout panel
    static JPanel checkoutPan;
    
    static JLabel checkoutTitle = new JLabel("Payment Details");
    static JLabel coInstructions = new JLabel("Choose your province:");
    
    static int[] customerPurchase = new int[10];//int array to keep track of how many items are bought at each index
    
    static JLabel cartProduct[] = new JLabel[10];//label for the items in the cart
    static JLabel cartNumber[] = new JLabel[10];//label for the number of each item in the cart
    static double[] cartTotal = new double[10];//the total of each item in the cart
    
    static JComboBox provinces = new JComboBox(new String[]{"Newfoundland and Labrador", 
        "Prince Edward Island", "Nova Scotia", "New Brunswick", "Quebec", "Ontario", "Manitoba", 
        "Saskatchewan", "Alberta", "British Columbia", "Yukon", "Northwest Territories", "Nunavut"});//an array of provinces for a combo box
    
    static String provinceSelected = "Newfoundland and Labrador";//starts with a random province to start
    
    //labels for the subtotal (base cost), the HST, and the final total
    static JLabel baseCost = new JLabel("");
    static JLabel HST = new JLabel("");
    static JLabel total = new JLabel("");
    static double base = 0;
    
    //button to either cancel the purchase, after seeing the costs
    static JButton cancelP = new JButton("Cancel Purchase");
    //button to confirm the purchase, after seeing the costs
    static JButton contP = new JButton("Confirm Purchase");
    
    //thank you panel
    static JPanel finalPan;
    static JLabel confirmed = new JLabel("Order Confirmed!");//new label that shows the order has been confirmed
    static JLabel ty = new JLabel("Thank you for shopping at Mcdonalds!");//thank you message for user
    static JLabel ccInstructions = new JLabel("Here is an in-store coupon: ");//thank you message for user
    static JButton finish = new JButton("Close");//close the panel, and return to welcome to finish the purchase

    //declaring main arrays used and important variables globally
    static String[] productNames = {"Big Mac", "McChicken", "Fries", "McMuffin", "McWrap", 
        "Coffee", "Soft Drink", "Smoothie", "", ""};//pre-initialized array for the products names
    static double[] productPrice = {5.99, 5.99, 3.49, 4.49, 4.49, 3.50, 2.50, 4.00, 0, 0};//pre-initialized array for the products prices
    static int[] productQuantity = {56, 93, 164, 60, 78, 293, 490, 39, 0, 0};//pre-initialized array for the products quantities
    static double revenue = 0;//revenue counter declaration
    static int customerNum = 0;//customer counter declaration
    static String password = "123"; //setting the password
    
    public GUIMcDonalds() { 
        //frame details
        setTitle("Online McDonalds");//title of the frame
        setSize(640, 480);//size of the frame
        setExtendedState(Frame.MAXIMIZED_BOTH);//making the frame full screen,(the combo box will not show properly otherwise)
        setLocationRelativeTo(null);//centering the frame in the screen
        
        //adding action listeners to the buttons in the welcome panel
        customerMode.addActionListener(this);
        managerMode.addActionListener(this);
        exitMode.addActionListener(this);
        
        //invoking the display mode method to show the welcome panel
        displayMode();
        //adding welcome panel to frame
        add(welcomePan);
        //making the frame visible
        setVisible(true);

        //adding action listener to the buttons in the password panel
        passwordField.addActionListener(this);
        enterPassword.addActionListener(this);
        exitPassword.addActionListener(this);
        
        //adding action listener to the buttons in the manager panel
        saveInventory.addActionListener(this);
        exitInventory.addActionListener(this);
        goCounters.addActionListener(this);
        
        //adding action listener to the buttons in the counter panel
        exitCounters.addActionListener(this);
        
        //adding action listener to the buttons in the customer panel
        goCheckout.addActionListener(this);
        cExitBack.addActionListener(this);
        
        //adding action listener to the buttons in the checkout panel
        cancelP.addActionListener(this);
        contP.addActionListener(this);
        provinces.addActionListener(this);
        
        //adding action listener to the button in the thank you panel
        finish.addActionListener(this);        
    }
    
    //void type method that reads from file to retrieve the stored inventory information
    static void loadFromFile(){
        
        //loop to go through all the indices the quantity, price and name arrays
        for(int i=0; i < productNames.length; i++){
            //reinitilizing all arrays to blank or 0
            productQuantity[i] = 0;
            productPrice[i] = 0;
            productNames[i] = "";            
        }
        //setting counter to 0
        int count = 0;
        //declaring buffered reader
        BufferedReader reader;
        //try catch validation:
	try{
            //the reader reads from a file called MCDonaldsProduct.txt
            reader = new BufferedReader(new FileReader("MCDonaldsProduct.txt"));
            String line = reader.readLine();
            //while the line is not blank
            while (line != null){
                //the products at the index of the counter is the text line
                productNames[count]=line;
                line = reader.readLine();
                //the quantity of each product at the index of the counter is the text line, type casted to an integer
                productQuantity[count]=Integer.parseInt(line);
                line = reader.readLine();
                //the price of each product at the index of the counter is the text line, type casted to a double
                productPrice[count]=Double.parseDouble(line);
                line = reader.readLine();
                //increading counter
                count++;
            }
            //close the buffered reader
            reader.close();
            //displays a pop up message to show the inventory has been read from the file
            JOptionPane.showMessageDialog(null, "Stock Inventory Read From File: MCDonaldsProduct.txt ", "Information ", JOptionPane.INFORMATION_MESSAGE);
        }
        //catches errors and exceptions
        catch (IOException e) {
        }
    }
    
    //void type method that writes to file, for the stored inventory information
    static void saveToFile(){
        //try catch validation:
        try{
            //the file writer writes to a file called MCDonaldsProduct.txt
            FileWriter fw = new FileWriter("MCDonaldsProduct.txt");
            
            //a loop that goes through all the products
            for(int i=0; i<productNames.length; i++){
                //writing the names to file
                fw.write(productNames[i]);
                fw.write("\n");//new line
                //changing the number of products to a string amd writing it to file
                fw.write(Integer.toString(productQuantity[i]));
                fw.write("\n");
                //changing the number of products to a string, with correct money format, and writing it to file
                fw.write(formatPrice(productPrice[i]));
                fw.write("\n");
            }
            //close the FileWriter so it can commit and save all changes
            fw.close();
            //displays a pop up message to show the inventory has been successfully writtem to file
            JOptionPane.showMessageDialog(null, "Stock Inventory Written to File: MCDonaldsProduct.txt ", "Information ", JOptionPane.INFORMATION_MESSAGE);
        } 
        //catches errors and exceptions
        catch (IOException e){
        }
    }  
    
    //method for the welcome panel
    public static void displayMode(){
        //initialize the panel
        welcomePan = new JPanel();
        //making the jPanel grid system: 
        //2 integer variables as the grid system
        int i = 5;
        int j = 3;
        
        //making a 2d array of empty jpanels as holders
        JPanel[][] panelHolder = new JPanel[i][j];
        //setting the gird layout to the dimensions specified above
        welcomePan.setLayout(new GridLayout(i,j));
        
        //nested for-loop for placing the individual panels, one by one
        for(int m = 0; m < i; m++) {
            for(int n = 0; n < j; n++) {
                //creating the new panel for the grid box
                panelHolder[m][n] = new JPanel();
                //adding the panels all to the main panel
                welcomePan.add(panelHolder[m][n]);
            }
        }
        //a nested for loop to colour the individual grids like a checker board
        for(int m = 0; m < i; m++) {
            for(int n = 0; n < j; n++) {
                //if the indexes of the sum of x and y is even
                if((m+n)%2==0){
                    //the small panel is set to red
                    panelHolder[m][n].setBackground(Color.red);
                    //if the indexes sum up to an odd number
                }else{
                    //the small panel is set to yellow
                    panelHolder[m][n].setBackground(Color.yellow);
                }
            }
        }
        //setting the size and the font of the title, welcome
        displayModeInstructions.setFont(new Font("SansSerif", Font.PLAIN, 18));    

        //adding the components in panel to the existing grids in the panels
        panelHolder[0][1].add(displayModeInstructions);
        panelHolder[2][1].add(customerMode);
        panelHolder[3][1].add(managerMode);
        panelHolder[4][1].add(exitMode);
        
        //adding an image of the mcdonalds M to the welcome screen
        panelHolder[1][1].add(new JLabel(new ImageIcon("McD.png")));
    }
    
    //the password panel, entering password
    public static void passwordMode(){
        //initializing the panel
        passwordPan = new JPanel();
        
        //making the jPanel grid system: 
        //2 integer variables as the grid system
        int i = 8;
        int j = 3;
        
        //making a 2d array of empty jpanels as holders
        JPanel[][] panelHolder = new JPanel[i][j];    
        //setting the gird layout to the dimensions specified above
        passwordPan.setLayout(new GridLayout(i,j));
        
        //nested for-loop for placing the individual panels, one by one
        for(int m = 0; m < i; m++) {
            for(int n = 0; n < j; n++) {
                //creating the new panel for the grid box
                panelHolder[m][n] = new JPanel();
                //adding the panels all to the main panel
                passwordPan.add(panelHolder[m][n]);
            }
        }    
        //setting the wrong password error message to red
        errorMsg.setForeground(Color.red);
        
        //adding the components in panel to the existing grids in the panels
        panelHolder[3][0].add(pswdInstructions);
        panelHolder[3][1].add(passwordField);
        panelHolder[3][1].add(enterPassword);
        panelHolder[5][1].add(errorMsg);
        panelHolder[6][1].add(exitPassword);
    }
    
    //what the manager sees: changing prices, stock and products
    public static void managerMode(){
        //initializing the panel
        managerPan = new JPanel();
        
        //making the jPanel grid system: 
        //2 integer variables as the grid system          
        int i = 12;
        int j = 5;
        
        //making a 2d array of empty jpanels as holders
        JPanel[][] panelHolder = new JPanel[i][j];  
        //setting the gird layout to the dimensions specified above
        managerPan.setLayout(new GridLayout(i,j));
        
        //nested for-loop for placing the individual panels, one by one
        for(int m = 0; m < i; m++) {
            for(int n = 0; n < j; n++) {
                //creating the new panel for the grid box
                panelHolder[m][n] = new JPanel();
                //adding the panels all to the main panel
                managerPan.add(panelHolder[m][n]);
            }
        }
        //adding the components in panel to the existing grids in the panels
        panelHolder[0][1].add(products);//all he products
        panelHolder[0][2].add(prices);//the the prices for the products
        panelHolder[0][3].add(quantities);// all the stock for the products
        
        //adding the buttons in panel to the existing grids in the panels
        panelHolder[11][3].add(saveInventory);
        panelHolder[11][4].add(goCounters);
        panelHolder[11][0].add(exitInventory);
        
        //going through all the values in the array
        for(int k = 0; k < 10; k++){
            //adding product text fields to the panel
            productList[k] = new JTextField(productNames[k], 8);
            panelHolder[k+1][1].add(productList[k]);
            
            //adding price text fields to the panel
            String tmpP = formatPrice(productPrice[k]);
            priceList[k] = new JTextField(tmpP, 5);
            panelHolder[k+1][2].add(priceList[k]);
            
            //adding quantities text fields to the panel
            String tmpQ = Integer.toString(productQuantity[k]);
            quantityList[k] = new JTextField(tmpQ,5);
            panelHolder[k+1][3].add(quantityList[k]);
        }
    }
    
    //the shop update stuff
    public static void counterMode(){
        //initializing the panel
        counterPan = new JPanel();
        
        //making the jPanel grid system: 
        //2 integer variables as the grid system                 
        int i = 4;
        int j = 3;
        
        //same structure as of the rest of the grid systems
        JPanel[][] panelHolder = new JPanel[i][j];    
        counterPan.setLayout(new GridLayout(i,j));
        
        for(int m = 0; m < i; m++) {
            for(int n = 0; n < j; n++) {
                panelHolder[m][n] = new JPanel();
                counterPan.add(panelHolder[m][n]);
            }
        }
        //adding the components in panel to the existing grids in the panels
        //setting the revenue label
        revenueLabel.setText("Totoal Revenue: $" + revenue);
        panelHolder[1][1].add(revenueLabel);
        //setting the customer counter label
        customerLabel.setText("Totoal Customer Count: " + customerNum);
        panelHolder[2][1].add(customerLabel);
        //adding the button to exit
        panelHolder[3][2].add(exitCounters);
        
    }
    
    //the customer's menu of items and adding to cart
    public static void customerMode(){
        //initializing the panel
        customerPan = new JPanel();
        
        //making the jPanel grid system: 
        //2 integer variables as the grid system            
        int i = 12;
        int j = 5;
        
        //same structure as of the rest of the grid systems
        JPanel[][] panelHolder = new JPanel[i][j];    
        customerPan.setLayout(new GridLayout(i,j));
        
        for(int m = 0; m < i; m++) {
            for(int n = 0; n < j; n++) {
                panelHolder[m][n] = new JPanel();
                customerPan.add(panelHolder[m][n]);
            }
        }
        //adding the text Fields in panel to the existing grids in the panels
        panelHolder[0][1].add(products);
        panelHolder[0][2].add(prices);
        panelHolder[0][3].add(cartNum);
        
        //adding the buttons in panel to the existing grids in the panels
        panelHolder[11][3].add(goCheckout);
        panelHolder[11][1].add(cExitBack);
        
        //setting the cart quantities to zero
        for(int k = 0; k < cQuantityList.length; k++){
            //making a text field with 0, prewritten
           cQuantityList[k] = new JTextField("0", 5);
        }
        
        //a loop going through all the arrays to add prices and products and text fields
        for(int k = 0; k < 10; k++){
            // if the product price is zero,OR there is no name for the product OR if there is no quantity for the product:
            if(productPrice[k]==0||productNames[k].equals("")||productQuantity[k]==0)
               continue;//skip this indes
            
            //adding product labels to the panel
            cProductList[k] = new JLabel(productNames[k]);
            panelHolder[k+1][1].add(cProductList[k]);
            
            //adding price labels to the panel
            String tmpP = "$"+formatPrice(productPrice[k]);//typecasting the prices
            cPriceList[k] = new JLabel(tmpP);
            
            //adding prices text fields to the panel
            panelHolder[k+1][2].add(cPriceList[k]);
            
            //adding quantities text fields to the panel
            panelHolder[k+1][3].add(cQuantityList[k]);
        }
    }
    
    //the checkout panel, with calulations and costs
    public static void checkoutMode(){
        //initializing the panel
        checkoutPan = new JPanel();
        
        //making the jPanel grid system: 
        //2 integer variables as the grid system            
        int i = 17;
        int j = 9;
        
        //same structure as of the rest of the grid systems
        JPanel[][] panelHolder = new JPanel[i][j];    
        checkoutPan.setLayout(new GridLayout(i,j));
        
        for(int m = 0; m < i; m++) {
            for(int n = 0; n < j; n++) {
                panelHolder[m][n] = new JPanel();
                checkoutPan.add(panelHolder[m][n]);
            }
        }
        //adding the components in panel to the existing grids in the panels
        panelHolder[14][1].add(provinces);
        panelHolder[12][1].add(coInstructions);
        panelHolder[16][1].add(cancelP);
        panelHolder[16][7].add(contP);
        
        //adding he title of the checkout panel so the user knows
        panelHolder[0][1].add(checkoutTitle);
        
        //a for loop running through all the index of the arrays for price, quantities.
        for(int k = 0, cnt = 0; k < 10; k++){
            if(customerPurchase[k]==0)
                continue;
            
            //adding signs and symbols labels to the panel to aid in user friendliness
            panelHolder[cnt+1][2].add(new JLabel("x"));
            panelHolder[cnt+1][4].add(new JLabel(" at           $"));
            panelHolder[cnt+1][6].add(new JLabel("... Total: "));
            
            //adding product names in a label if it passes through the condition, 
            //which is if the customer purchases is not 0
            panelHolder[cnt+1][1].add(new JLabel(productNames[k]));
            
            //adding price labels to the panel, if it passes through the condition
            String tmpP = formatPrice(productPrice[k]);
            panelHolder[cnt+1][5].add(new JLabel(tmpP));
            
            //adding quantities text fields to the panel, if it passes through the condition
            String tmpQ = Integer.toString(customerPurchase[k]);
            panelHolder[cnt+1][3].add(new JLabel(tmpQ));
            
            //calculating the total cost for one item
            cartTotal[k] = customerPurchase[k]* productPrice[k];
            //formatting prices
            String tmpT = "$"+formatPrice(cartTotal[k]);
            //adding the label on to the panel
            panelHolder[cnt+1][7].add(new JLabel(tmpT));
            //accumulate
            cnt++;
        }
        //declaring the base cost variable, or the subtotal
        base = baseCost();
        //declaring the tax variable, or the HST
        double tax = base*taxRate();
        //declaring the overall total
        double total1 = base+tax;
        
        //configuring out the costs to the panel
        baseCost = new JLabel("SUBTOTAL: $" + formatPrice(base));
        HST = new JLabel("TAX: $" + formatPrice(tax));
        total = new JLabel("TOTAL: $" + formatPrice(total1));
        
        //adding the costs and cululations on to the panel
        panelHolder[13][7].add(baseCost);
        panelHolder[14][7].add(HST);
        panelHolder[15][7].add(total);
    }
    
    //the final stage of checkout
    public static void finalMode(){
        //initializing the panel
        finalPan = new JPanel();
        
        //making the jPanel grid system: 
        //2 integer variables as the grid system            
        int i = 4;
        int j = 3;
        
        //same pattern/way of creaking the grip system as above
        JPanel[][] panelHolder = new JPanel[i][j];    
        finalPan.setLayout(new GridLayout(i,j));
        
        for(int m = 0; m < i; m++) {
            for(int n = 0; n < j; n++) {
                panelHolder[m][n] = new JPanel();
                finalPan.add(panelHolder[m][n]);
            }
        }
        JLabel cCode = new JLabel(Integer.toString(genCoupon()));//coupon code
        
        //adding the final messages and thank yous on to the panel
        panelHolder[0][1].add(confirmed);
        panelHolder[1][1].add(ty);
        panelHolder[2][1].add(cCode);
        panelHolder[2][0].add(ccInstructions);
        panelHolder[3][1].add(finish);

    }
    //==========================================================================
    
    //method that calculates the base cost, to the sub total without tax
    public static double baseCost(){
        double subtotal = 0;
        //a for loop that accumlates the total from all the products in the cart
        for (int i = 0; i < productNames.length; i++){
            subtotal += cartTotal[i];
        }
        //return type that returns the value
        return subtotal;
    }
    
    //method allows the new items and quanities to save to the origional arrays
    public static void saveInventory(){
        for(int i = 0; i < productNames.length; i++){
            //makes the values at all indexes of the arrays identical
            productNames[i] = productList[i].getText();
            productPrice[i] =  Double.parseDouble(priceList[i].getText());
            productQuantity[i] =  Integer.parseInt(quantityList[i].getText());
        }
    }
    
    //updates invertory after the user has made purchases
    public static void updateInventory(){
        for(int i = 0; i < productNames.length; i++){
            //subtracts the origional array to the numbers in the cart of the user
            productQuantity[i] -=  Integer.parseInt(cQuantityList[i].getText());
        }
    }
    
    //a file.io method that writes the store updates to file
    public static void updateSales(){
        //revenue accumulates as base add up
        revenue += base;
        //customer increases each time the method is called,
        //(or each time the final checkout button is pressed)
        customerNum ++;
        
        try{
            //declaring a new filewriter to write to file
            FileWriter fw = new FileWriter("salesUpdate.txt",true);
            
            //writing the revenue to file
            fw.write("Revenue: $");
            fw.write(formatPrice(base));
            fw.write(",");
            //total revenue since running the program
            fw.write(" Total Revenue (since running program): $");
            fw.write(formatPrice(revenue));//formatting string number to 2 decimal places
            fw.write(",");
            //total customers used this program since running the program
            fw.write(" Customer Count (since running program): ");
            fw.write(Integer.toString(customerNum));
            fw.write(".\n");
            fw.close();  
            //pop-up frame that shows up when imformation is successfully updated to file
            JOptionPane.showMessageDialog(null, "Sales Updated to File: salesUpdate.txt ", "Information ", JOptionPane.INFORMATION_MESSAGE);
        } 
        //actches errors and exceptions
        catch (IOException e){
        }
    }
    
    //validate that the numbers of the manager is typing is right
    public static boolean validateQuantityM(){
        
        for (int i = 0; i < productNames.length; i++){
            
            //setting the quantity variable
            String mQuantity = quantityList[i].getText();
            int quantity = 0;
            
            try{
                quantity = Integer.parseInt(mQuantity);
            }catch (Exception e){
                //show popup error panel
                JOptionPane.showMessageDialog(null, productNames[i]+": Invalid Quantity Entered ", "ERROR ", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
           //negative num
            if(quantity < 0){
                JOptionPane.showMessageDialog(null, productNames[i]+": Quantity Cannot Be Negative ", "ERROR ", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
                        String mPrice = priceList[i].getText();
            double price = 0;
            
            try{
                price = Double.parseDouble(mPrice);
            }catch (Exception e){
                //show popup error panel
                JOptionPane.showMessageDialog(null, productNames[i]+": Invalid Price Entered ", "ERROR ", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
           //negative price
            if(price < 0){
                //show popup error panel
                JOptionPane.showMessageDialog(null, productNames[i]+": Price Cannot Be Negative ", "ERROR ", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        return true;
    }
    
    //validate that the numbers of the customer typing are right
    public static boolean validateQuantityC(){
        
        for (int i = 0; i < productNames.length; i++){
            //setting the quantity variable
            String cQuantity = cQuantityList[i].getText();
            int quantity = 0;
            
            try{
                quantity = Integer.parseInt(cQuantity);
            }catch (Exception e){
                //show popup error panel
                JOptionPane.showMessageDialog(null, productNames[i]+": Invalid Number ", "ERROR " ,JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
           //negative num
            if(quantity < 0){
                //show popup error panel
                JOptionPane.showMessageDialog(null, productNames[i]+": Invalid Quantity ", "ERROR " ,JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
           //negative price
            if(quantity > productQuantity[i]){
                //show popup error panel
                JOptionPane.showMessageDialog(null, productNames[i]+" Out of Stock ", "ERROR " ,JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        return true;
    }
    //generates coupon code
    public static int genCoupon(){
        int couponCode = (int)(Math.random()*1000000000); 
        return couponCode;
    }
    
    //method that selects which province the user is in
    //method that determins tax rate
    public static double taxRate(){    
        
        double taxRate = 0;
        switch (provinceSelected) {
            case "Newfoundland and Labrador":
                taxRate = 0.15;
                break;
            case "Prince Edward Island":
                taxRate = 0.14;
                break;
            case "Nova Scotia":
                taxRate = 0.15;
                break;
            case "New Brunswick":
                taxRate = 0.15;
                break;
            case "Quebec":
                taxRate = 0.14975;
                break;
            case "Ontario":
                taxRate = 0.13;
                break;
            case "Manitoba":
                taxRate = 0.13;
                break;
            case "Saskatchewan":
                taxRate = 0.11;
                break;
            case "Alberta":
                taxRate = 0.05;
                break;
            case "British Columbia":
                taxRate = 0.05;
                break;
            case "Yukon":
                taxRate = 0.05;
                break;
            case "Northwest Territories":
                taxRate = 0.05;
                break;
            case "Nunavut":
                taxRate = 0.05;
                break;
        }
        return taxRate;
    }
    //formatting prices to 2 decimal places
    public static String formatPrice(double price){
        String formatted = String.format( "%.2f", price);
        return formatted;
    }
    //==========================================================================

    public void actionPerformed(ActionEvent event) {
        //action listener, getting the source/name of the buttons
        Object com = event.getSource();
        String command = event.getActionCommand(); 
        //if the big exit button is pressed
        if(com.equals(exitMode)){
            //save inventory to file
            saveToFile();
            //exit the program and window
            System.exit(0);
        }
        // if the manager button is pressed
        else if(com.equals(managerMode)){
            welcomePan.setVisible(false);
            passwordMode();
            passwordPan.setVisible(true);
            frame1.remove(welcomePan);
            frame1.add(passwordPan);
            frame1.revalidate();
        } 
        // if the passwordbutton is pressed
        else if(com.equals(enterPassword)){

           if(String.valueOf(passwordField.getPassword()).equals(password)){
               errorMsg.setText("");
               passwordPan.setVisible(false);
               managerMode();
               managerPan.setVisible(true);
               frame1.remove(passwordPan);
               frame1.add(managerPan);
               frame1.revalidate();
            }
            else{
               errorMsg.setText("Wrong password!");
            }
        }
         // if the exit/go back from password button is pressed
        else if(com.equals(exitPassword)){
            //removes current panel
            //goes to welcome panel              
            passwordField.setText("");
            passwordPan.setVisible(false);
            displayMode();
            welcomePan.setVisible(true);
            frame1.remove(passwordPan);
            frame1.add(welcomePan);
            frame1.revalidate();
        }
        // if the go to customer mode button is pressed
        else if(com.equals(customerMode)){
            //removes current panel
            //goes to customer panel              
            passwordField.setText("");
            welcomePan.setVisible(false);
            customerMode();
            customerPan.setVisible(true);
            frame1.remove(welcomePan);
            frame1.add(customerPan);
            frame1.revalidate();
        }
        // if the save button is pressed
        else if(com.equals(saveInventory)){
            if(!validateQuantityM())
                return;
            passwordField.setText("");
            managerPan.setVisible(false);
            saveInventory();
            displayMode();
            welcomePan.setVisible(true);
            frame1.remove(managerPan);
            frame1.add(welcomePan);
            frame1.revalidate();
        }
        // if the exit/go back invenotory button is pressed
        else if(com.equals(exitInventory)){
            //removes current panel
            //goes to final panel              
            passwordField.setText("");
            managerPan.setVisible(false);
            displayMode();
            welcomePan.setVisible(true);
            frame1.remove(managerPan);
            frame1.add(welcomePan);
            frame1.revalidate();
        }
        // if the exit/go back from counters button is pressed
        else if(com.equals(exitCounters)){
            //removes current panel
            //goes to manager panel              
            passwordField.setText("");
            counterPan.setVisible(false);
            managerMode();
            managerPan.setVisible(true);
            frame1.remove(counterPan);
            frame1.add(managerPan);
            frame1.revalidate();
        }  
        // if the go to the counters button is pressed
        else if(com.equals(goCounters)){
            //removes current panel
            //goes to counter panel  
            passwordField.setText("");
            managerPan.setVisible(false);
            counterMode();
            counterPan.setVisible(true);
            frame1.remove(managerPan);
            frame1.add(counterPan);
            frame1.revalidate();
        }
        // if the go checkout  button is pressed
        else if(com.equals(goCheckout)){
            if(!validateQuantityC())
                return;
            customerPan.setVisible(false);
            for(int i = 0; i < cQuantityList.length; i++){        
                String temp = cQuantityList[i].getText();
                customerPurchase[i] = Integer.parseInt(temp);
            }
            checkoutMode();
            checkoutPan.setVisible(true);
            frame1.remove(customerPan);
            frame1.add(checkoutPan);
            frame1.revalidate();
        }
        // if the exit/go back  button is pressed
        else if(com.equals(cExitBack)){
            //removes current panel
            //goes to welcome panel              
            passwordField.setText("");
            customerPan.setVisible(false);
            displayMode();
            welcomePan.setVisible(true);
            frame1.remove(customerPan);
            frame1.add(welcomePan);
            frame1.revalidate();
        }
        else if(com.equals(provinces)){
            
            //getting the province selected from the combo box
            provinceSelected = (String)provinces.getSelectedItem();
            
           //calculates tac and total cost
            double tax = base*taxRate();
            double total1 = base+tax;
            
            //sets text of the label for the tax and total cost of purchase
            HST.setText("TAX: $" + formatPrice(tax));
            total.setText("TOTAL: $"+ formatPrice(total1));
        }
        // if the contimue purchase button is pressed
        else if(com.equals(contP)){
            //removes current panel
            //goes to final panel            
            checkoutPan.setVisible(false);
            updateInventory();
            finalMode();
            finalPan.setVisible(true);
            frame1.remove(checkoutPan);
            frame1.add(finalPan);
            frame1.revalidate();
        }
        // if the cancel purchase button is pressed
        else if(com.equals(cancelP)){
            //removes current panel
            //goes to welcome panel            
            checkoutPan.setVisible(false);
            displayMode();
            welcomePan.setVisible(true);
            frame1.remove(checkoutPan);
            frame1.add(welcomePan);
            frame1.revalidate();
        }
        // if the finish button is pressed
        else if(com.equals(finish)){
            //removes current panel
            //goes to final panel
            finalPan.setVisible(false);
            displayMode();
            updateSales();
            welcomePan.setVisible(true);
            frame1.remove(finalPan);
            frame1.add(welcomePan);
            frame1.revalidate();
        }
    }
    //main method
    public static void main(String[] args) {
        //loading inventory from file
        loadFromFile();
        //starting the GUI
        frame1 = new GUIMcDonalds();
    }
}
