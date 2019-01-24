class Token {
     String token,type;
      int row,column;
    Token(String token,String type,int row,int column)
    {
        this.column=column;this.row=row;this.token=token;this.type=type;
    }
    protected void display()
    {
        String format = "%15s%20s%10s%10s%n";
        System.out.printf(format,this.token,this.type,this.row,this.column);
    }

}
