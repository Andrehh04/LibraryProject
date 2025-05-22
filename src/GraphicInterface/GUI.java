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
            JDialog dialog = new JDialog(frame,"Select reding status",true);
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
            library.setStrategy(library.createSearchByAuthorStrategy());
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



        //tabella contenente i libri da visualizare
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);


        toolBar.setLayout(new FlowLayout());
        frame.add(toolBar, BorderLayout.NORTH);
        frame.setPreferredSize(new Dimension(1000, 800));
        frame.pack();
        frame.setVisible(true);
    }
}
