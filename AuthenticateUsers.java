
import java.util.Scanner; 

public class AuthenticateUsers
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in); //Scanner object created.

		//Multi-Dimensional Array contents. 
		String[][] user_info = {{"tom", "brady12"}, {"satish", "csci215"}, {"raul", "abc123"}, {"sanchez", "boston"}, {"penmatsa", "java"}};

		String user = ""; //store user info from user input in user variable.

		String password = ""; //store password info from user input in password variable. 

		boolean userData = true; //boolean field for correct data.

		boolean passData = false; //boolean field for password data.

		System.out.println("Enter username: ");

		user = input.nextLine();

		while (true) //while loop to check if user name matches info in the array.
		{
			for (String[] users: user_info) //enhanced for loop with the array contents.
			{
				if (users[0].equals(user)) //if while going through the loop user content matches, break.
				{
					userData = true;
					break;
				}
			}
			if (userData == true) //IF true, move onto password input. ELSE re-enter information.
			{
				break;
			}
			else
			{
				System.out.print("Invalid username: Please re-enter: ");
				user = input.nextLine();
			}
		}

		System.out.println("Enter password: ");

		password = input.nextLine(); //user input password data is stored.

		for (String[] users: user_info) //enhanced for loop with the array contents.
		{
			if (users[0].equals(user) && users[1].equals(password)) //if content of the user and password match, break the loop.
			{
				passData = true;
				break;
			}
		}

		if (passData == true) //IF pass is true with the user, print the following.
		{
			System.out.println("Login successful");
		}
		else
		{
			System.out.println("Invalid Password");
		}


	}
}