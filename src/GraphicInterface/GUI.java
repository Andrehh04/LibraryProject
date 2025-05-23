package GraphicInterface;

import Facade.Library;
import Facade.LibraryImpl;
import Filtering.FilterByGenre;
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
        JButton modifyButton = new JButton("Modify book");
        modifyButton.setPreferredSize(new Dimension(150, 25));
        toolBar2.add(addButton);
        toolBar2.add(removeButton);
        toolBar2.add(modifyButton);


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
           JDialog dialog = new JDialog();
           JTextField title = new JTextField(10);
           JTextField author = new JTextField(10);
           JTextField isbn = new JTextField(10);
           JTextField genre = new JTextField(10);
           //JTextField rating =
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
