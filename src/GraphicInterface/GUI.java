package GraphicInterface;

import Facade.Library;
import Facade.LibraryImpl;
import Filtering.FilterByGenre;
import Model.Book;
import Model.Rating;
import Model.ReadingStatus;
import Ordering.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI {

    static{
        try{
            LibraryImpl.INSTANCE.load("libreriafile.json");
        }catch(IOException e){
            System.out.println("Loading error: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        LibraryImpl library = LibraryImpl.INSTANCE;


        JFrame frame = new JFrame("Libreria Personale");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LibraryModel model = new LibraryModel();

        JToolBar toolBar = new JToolBar();

        //Bottoni per le varie operazioni
        JButton undoButton = new JButton("Undo");
        undoButton.setPreferredSize(new Dimension(50, 25));
        JButton redoButton = new JButton("Redo");
        redoButton.setPreferredSize(new Dimension(50, 25));
        JButton researchButton = new JButton("Search");
        researchButton.setPreferredSize(new Dimension(100, 25));
        JButton filterButton = new JButton("Filter");
        filterButton.setPreferredSize(new Dimension(100, 25));
        JButton orderButton = new JButton("Order");
        orderButton.setPreferredSize(new Dimension(100, 25));
        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(50, 25));
        JButton showButton = new JButton("Show Books");
        showButton.setPreferredSize(new Dimension(100, 25));
        toolBar.add(undoButton);
        toolBar.addSeparator(new Dimension(1,0));
        toolBar.add(redoButton);
        toolBar.addSeparator(new Dimension(50,0));
        toolBar.add(researchButton);
        toolBar.addSeparator(new Dimension(20,0));
        toolBar.add(filterButton);
        toolBar.addSeparator(new Dimension(20,0));
        toolBar.add(orderButton);
        toolBar.addSeparator(new Dimension(40,0));
        toolBar.add(saveButton);
        toolBar.addSeparator(new Dimension(40,0));
        toolBar.add(showButton);


        //Seconda toolbar con i bottoni per aggiungere rimuovere e modificare un libro
        JToolBar toolBar2 = new JToolBar();
        JButton addButton = new JButton("Add book");
        addButton.setPreferredSize(new Dimension(150, 25));
        JButton removeButton = new JButton("Remove book");
        removeButton.setPreferredSize(new Dimension(150, 25));
        JButton modifyGenreButton = new JButton("Modify genre");
        modifyGenreButton.setPreferredSize(new Dimension(150, 25));
        JButton modifyStatusButton = new JButton("Modify status");
        modifyStatusButton.setPreferredSize(new Dimension(150, 25));
        JButton modifyRatingButton = new JButton("Modify rating");
        modifyRatingButton.setPreferredSize(new Dimension(150, 25));
        toolBar2.add(addButton);
        toolBar2.add(removeButton);
        toolBar2.add(modifyGenreButton);
        toolBar2.add(modifyStatusButton);
        toolBar2.add(modifyRatingButton);


        //listener per bottone show
        showButton.addActionListener(e -> {
           model.setBooks(library.getBooks());
        });


        //listener per il bottone save
        saveButton.addActionListener(e -> {
            try{
                library.save(library.getBooks(),"libreriafile.json");
            }catch(IOException exception){
                System.out.println("Saving error: " + exception.getMessage());
            }
        });


        //menù a tendina per il search button
        JPopupMenu popSearchMenu = new JPopupMenu();
        JMenuItem itSearch1 = new JMenuItem("Search by title");
        JMenuItem itSearch2 = new JMenuItem("Search by author");
        JMenuItem itSearch3 = new JMenuItem("Search by ISBN");
        popSearchMenu.add(itSearch1);
        popSearchMenu.add(itSearch2);
        popSearchMenu.add(itSearch3);
        //listener bottone search
        researchButton.addActionListener(e -> {
            popSearchMenu.show(researchButton, 0, researchButton.getHeight());
        });
        //listener item popup menu
        itSearch1.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter a title");
            if (input != null) {
                library.setStrategy(library.createSearchByTitleStrategy());
                model.setBooks(library.searchBook(library.getBooks(), input));
            }
        });
        itSearch2.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter an author");
            if (input != null) {
                library.setStrategy(library.createSearchByAuthorStrategy());
                model.setBooks(library.searchBook(library.getBooks(), input));
            }
        });
        itSearch3.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter an ISBN");
            if (input != null) {
                library.setStrategy(library.createSearchByISBNStrategy());
                model.setBooks(library.searchBook(library.getBooks(), input));
            }
        });



        //menù a tendina per il filter button
        JPopupMenu popFilterMenu = new JPopupMenu();
        JMenuItem itFilter1 = new JMenuItem("Filter by genre");
        JMenuItem itFilter2 = new JMenuItem("Filter by status");
        popFilterMenu.add(itFilter1);
        popFilterMenu.add(itFilter2);

        //action listener per il bottone filter
        filterButton.addActionListener(e -> {
            popFilterMenu.show(filterButton, 0, filterButton.getHeight());
        });

        //action listener per il popup menu
        itFilter1.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter a genre");
            if (input != null) {
                library.setStrategy(library.createFilterByGenreStrategy());
                model.setBooks(library.filter(library.getBooks(),input));

            }
        });
        itFilter2.addActionListener(e -> {
            JDialog dialog = new JDialog(frame,"Select reading status",true);
            dialog.setLayout(new GridLayout(3,1));
            dialog.setSize(150,200);
            dialog.setLocationRelativeTo(frame);
            JButton read = new JButton("Read");
            JButton reading = new JButton("Reading");
            JButton toread = new JButton("To read");
            dialog.add(read,0);
            dialog.add(reading,1);
            dialog.add(toread,2);
            read.addActionListener(event -> {
                library.setStrategy(library.createFilterByStatusStrategy());
                model.setBooks(library.filter(library.getBooks(),"read"));
                dialog.dispose();
            });
            reading.addActionListener(event -> {
                library.setStrategy(library.createFilterByStatusStrategy());
                model.setBooks(library.filter(library.getBooks(),"reading"));
                dialog.dispose();
            });
            toread.addActionListener(event -> {
                library.setStrategy(library.createFilterByStatusStrategy());
                model.setBooks(library.filter(library.getBooks(),"toread"));
                dialog.dispose();
            });
            dialog.setVisible(true);
        });



        //menù a tendina per l'order button
        JPopupMenu popOrderMenu = new JPopupMenu();
        JMenuItem itOrder1 = new JMenuItem("Order by title");
        JMenuItem itOrder2 = new JMenuItem("Order by author");
        JMenuItem itOrder3 = new JMenuItem("Order by genre");
        JMenuItem itOrder4 = new JMenuItem("Order by status");
        popOrderMenu.add(itOrder1);
        popOrderMenu.add(itOrder2);
        popOrderMenu.add(itOrder3);
        popOrderMenu.add(itOrder4);

        //listener per il bottone order
        orderButton.addActionListener(e -> {
            popOrderMenu.show(orderButton, 0, orderButton.getHeight());
        });

        //listener per il popup menu
        itOrder1.addActionListener(e -> {
            library.setStrategy(library.createOrderByTitleStrategy());
            model.setBooks(library.Order(library.getBooks()));
        });
        itOrder2.addActionListener(e -> {
            library.setStrategy(library.createOrderByAuthorStrategy());
            model.setBooks(library.Order(library.getBooks()));
        });
        itOrder3.addActionListener(e -> {
           library.setStrategy(library.createOrderByGenreStrategy());
           model.setBooks(library.Order(library.getBooks()));
        });
        itOrder4.addActionListener(e -> {
           library.setStrategy(library.createOrderByStatusStrategy());
           model.setBooks(library.Order(library.getBooks()));
        });




        //tabella contenente i libri da visualizzare
        JTable table = new JTable(model);
        table.setRowHeight(50);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(800,300));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 10, 10, 50));
        frame.add(panel);


        //action listener remove button
        removeButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                JDialog dialog = new JDialog(frame, "Are you sure you want to delete?", true);
                JButton yes = new JButton("Yes");
                JButton no = new JButton("No");
                dialog.setLayout(new GridLayout(1,2));
                dialog.setSize(200,80);
                dialog.setLocationRelativeTo(frame);
                dialog.add(yes);
                dialog.add(no);
                yes.addActionListener(event ->{
                    library.handle(library.createRemoveCommand(model.getBookAt(row)));
                    model.refresh();
                    dialog.dispose();
                });
                no.addActionListener(event ->{
                   dialog.dispose();
                });
                dialog.setVisible(true);
            }
        });


        //action listener add button
        addButton.addActionListener(e -> {
            JDialog dialog = new JDialog(frame,"Add book",true);
            dialog.setLayout(new BorderLayout());

            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new BoxLayout(inputPanel,BoxLayout.Y_AXIS));
            JPanel labelPanel = new JPanel();
            labelPanel.setLayout(new BoxLayout(labelPanel,BoxLayout.Y_AXIS));
            JPanel confirmPanel = new JPanel();

            JButton confirm = new JButton("Confirm");
            JButton cancel = new JButton("Cancel");

            JTextField title = new JTextField(10);
            title.setMaximumSize(new Dimension(150,25));
            JTextField author = new JTextField(10);
            author.setMaximumSize(new Dimension(150,25));
            JTextField isbn = new JTextField(10);
            isbn.setMaximumSize(new Dimension(150,25));
            JTextField genre = new JTextField(10);
            genre.setMaximumSize(new Dimension(150,25));
            JComboBox<ReadingStatus> statusCombo = new JComboBox<>(ReadingStatus.values());
            statusCombo.setMaximumSize(new Dimension(150,25));
            JComboBox<Rating> ratingCombo = new JComboBox<>(Rating.values());
            ratingCombo.setMaximumSize(new Dimension(150,25));

            labelPanel.add(Box.createVerticalStrut(32));
            labelPanel.add(new JLabel("Title:"));
            labelPanel.add(Box.createVerticalStrut(40));
            labelPanel.add(new JLabel("Author:"));
            labelPanel.add(Box.createVerticalStrut(36));
            labelPanel.add(new JLabel("ISBN:"));
            labelPanel.add(Box.createVerticalStrut(40));
            labelPanel.add(new JLabel("Genre:"));
            labelPanel.add(Box.createVerticalStrut(40));
            labelPanel.add(new JLabel("Reading Status:"));
            labelPanel.add(Box.createVerticalStrut(38));
            labelPanel.add(new JLabel("Rating:"));

            inputPanel.add(Box.createVerticalStrut(30));
            inputPanel.add(title);
            inputPanel.add(Box.createVerticalStrut(30));
            inputPanel.add(author);
            inputPanel.add(Box.createVerticalStrut(30));
            inputPanel.add(isbn);
            inputPanel.add(Box.createVerticalStrut(30));
            inputPanel.add(genre);
            inputPanel.add(Box.createVerticalStrut(30));
            inputPanel.add(statusCombo);
            inputPanel.add(Box.createVerticalStrut(30));
            inputPanel.add(ratingCombo);
            inputPanel.add(Box.createVerticalStrut(30));

            confirmPanel.add(confirm);
            confirmPanel.add(cancel);
            dialog.add(labelPanel,BorderLayout.WEST);
            dialog.add(inputPanel,BorderLayout.CENTER);
            dialog.add(confirmPanel,BorderLayout.SOUTH);


            //Action listener confirm button
            confirm.addActionListener(event -> {
                if(!(title.getText().trim().isEmpty() || author.getText().trim().isEmpty() || isbn.getText().trim().isEmpty())) {
                    Book.Builder builder = new Book.Builder(title.getText(),author.getText(),isbn.getText());
                    Book book = builder.setGenre(genre.getText()).
                            setReadingStatus((ReadingStatus)statusCombo.getSelectedItem()).
                            setRating((Rating)ratingCombo.getSelectedItem()).build();
                    library.handle(library.createAddCommand(book));
                    model.refresh();
                    dialog.dispose();
                }else{
                    JOptionPane.showMessageDialog(frame, "Please fill all the required fields");
                }
            });

            //Action listener cancel button
            cancel.addActionListener(event ->{
                dialog.dispose();
            });

            dialog.setSize(300,450);
            dialog.setResizable(false);
            dialog.setLocationRelativeTo(frame);
            dialog.setVisible(true);
        });//fine action listener add button


        //Action listener modifyGenre button
         modifyGenreButton.addActionListener(e -> {
             if(table.getSelectedRow()!=-1){
                 Book book = model.getBookAt(table.getSelectedRow());
                 String input = JOptionPane.showInputDialog(frame, "Enter a genre");
                 if (input != null) {
                     library.handle(library.createModifyGenreCommand(book,input));
                 }
                 model.refresh();
             }

         });

         //Action listener modifyStatus button
        modifyStatusButton.addActionListener(e -> {
            if(table.getSelectedRow()!=-1){
                JDialog dialog = new JDialog(frame,"Modify status",true);
                Book book = model.getBookAt(table.getSelectedRow());
                dialog.setLayout(new BorderLayout());
                JPanel inputPanel = new JPanel();
                JPanel confirmPanel = new JPanel();
                JButton confirm = new JButton("Confirm");
                JButton cancel = new JButton("Cancel");
                confirmPanel.add(confirm);
                confirmPanel.add(cancel);
                JComboBox<ReadingStatus> statusCombo = new JComboBox<>(ReadingStatus.values());
                statusCombo.setMaximumSize(new Dimension(150,25));
                statusCombo.setSelectedItem(book.getReadingStatus());
                inputPanel.add(statusCombo);
                dialog.add(inputPanel,BorderLayout.CENTER);
                dialog.add(confirmPanel,BorderLayout.SOUTH);

                confirm.addActionListener(event ->{
                    library.handle(library.createModifyStatusCommand(book,(ReadingStatus)statusCombo.getSelectedItem()));
                    model.refresh();
                    dialog.dispose();
                });

                cancel.addActionListener(event ->{
                    dialog.dispose();
                });

                dialog.setSize(300,250);
                dialog.setResizable(false);
                dialog.setLocationRelativeTo(frame);
                dialog.setVisible(true);
            }
        });


        //Action listener modifyRating button
        modifyRatingButton.addActionListener(e -> {
            if(table.getSelectedRow()!=-1){
                JDialog dialog = new JDialog(frame,"Modify rating",true);
                Book book = model.getBookAt(table.getSelectedRow());
                dialog.setLayout(new BorderLayout());
                JPanel inputPanel = new JPanel();
                JPanel confirmPanel = new JPanel();
                JButton confirm = new JButton("Confirm");
                JButton cancel = new JButton("Cancel");
                confirmPanel.add(confirm);
                confirmPanel.add(cancel);
                JComboBox<Rating> ratingCombo = new JComboBox<>(Rating.values());
                ratingCombo.setMaximumSize(new Dimension(150,25));
                ratingCombo.setSelectedItem(book.getRating());
                inputPanel.add(ratingCombo);
                dialog.add(inputPanel,BorderLayout.CENTER);
                dialog.add(confirmPanel,BorderLayout.SOUTH);

                confirm.addActionListener(event ->{
                    library.handle(library.createModifyRatingCommand(book,(Rating)ratingCombo.getSelectedItem()));
                    model.refresh();
                    dialog.dispose();
                });

                cancel.addActionListener(event ->{
                    dialog.dispose();
                });

                dialog.setSize(300,250);
                dialog.setResizable(false);
                dialog.setLocationRelativeTo(frame);
                dialog.setVisible(true);
            }
        });


        //Action listener undo button
        undoButton.addActionListener(e ->{
            library.undo();
            model.refresh();
        });


        //Action listener redo button
        redoButton.addActionListener(e ->{
            library.redo();
            model.refresh();
        });



        toolBar.setLayout(new FlowLayout());
        toolBar2.setLayout(new FlowLayout());
        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new BoxLayout(toolbarPanel, BoxLayout.Y_AXIS));
        toolbarPanel.add(toolBar);
        toolbarPanel.add(toolBar2);
        frame.add(toolbarPanel, BorderLayout.NORTH);

        frame.setPreferredSize(new Dimension(1000, 800));
        frame.pack();
        frame.setVisible(true);
    }
}
