package project2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;


/**
 * CMSC405
 * David Mejia
 * Sample project provided was extended to complete project 2
 * Will use JOGL to create different shapes.  Rotations
 * can be applied with the arrow keys. the page up
 * key, and the page down key will zoom in and out.  The home key will set
 * all rotations and placement to 0, and scale to 1.  Initial rotations about the
 * x, y, and z axes are 15, -15, and 0. A D W S keys will translate the shapes to the side or up and down.
 */
public class shapes extends GLJPanel implements GLEventListener, KeyListener {
    
    /**
     * A main routine to create and show a window that contains a
     * panel of type shapes.  The program ends when the
     * user closes the window.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Project 2 -- ARROW KEYS ROTATE, A D W S KEYS MOVE, PAGE UP DOWN ZOOM IN OUT");
        shapes panel = new shapes();
        window.setContentPane(panel);
        window.pack();
        window.setLocation(50,50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        panel.requestFocusInWindow();

    }
    
    /**
     * Constructor for class shapes.
     */
    public shapes() {
        super( new GLCapabilities(null) ); // Makes a panel with default OpenGL "capabilities".
        setPreferredSize( new Dimension(640,480) );
        addGLEventListener(this); // A listener is essential! The listener is where the OpenGL programming lives.
        addKeyListener(this);
    }
    
    //-------------------- methods to draw the shapes ----------------------
    
    double rotateX = 15;    // rotations of the shapes about the axes
    double rotateY = -15;
    double rotateZ = 0;
    double scalex = 1;
    double scaley = 1;
    double scalez = 1;
    double moveX = 0;
    double moveY = 0;
    double moveZ = 0;
    
    private void square(GL2 gl2, double r, double g, double b) {
        gl2.glColor3d(r,g,b);
        gl2.glBegin(GL2.GL_TRIANGLE_FAN);
        gl2.glVertex3d(-0.5, -0.5, 0.5);
        gl2.glVertex3d(0.5, -0.5, 0.5);
        gl2.glVertex3d(0.5, 0.5, 0.5);
        gl2.glVertex3d(-0.5, 0.5, 0.5);
        gl2.glEnd();
    }

    /**
     * this shape is provided by the project example
     * @param gl2
     * @param size
     */
    private void cube(GL2 gl2, double size) {
        gl2.glPushMatrix();
        gl2.glScaled(size,size,size); // scale unit cube to desired size
        // Move the squares to offset 3,3 
        gl2.glTranslated(3,3,0);
        square(gl2,1, 0, 0); // red front face
        
        gl2.glPushMatrix();
        gl2.glRotated(90, 0, 1, 0);
       
        square(gl2,0, 1, 0); // green right face
        gl2.glPopMatrix();
        
        gl2.glPushMatrix();
        gl2.glRotated(-90, 1, 0, 0);
        square(gl2,0, 0, 1); // blue top face
        gl2.glPopMatrix();
        
        gl2.glPushMatrix();
        gl2.glRotated(180, 0, 1, 0);
        square(gl2,0, 1, 1); // cyan back face
        gl2.glPopMatrix();
        
        gl2.glPushMatrix();
        gl2.glRotated(-90, 0, 1, 0);
        square(gl2,1, 0, 1); // magenta left face
        gl2.glPopMatrix();
        
        gl2.glPushMatrix();
        gl2.glRotated(90, 1, 0, 0);
        square(gl2,1, 1, 0); // yellow bottom face
        gl2.glPopMatrix();
        
        gl2.glPopMatrix(); // Restore matrix to its state before cube() was called.
    }
    
    
    //-------------------- GLEventListener Methods -------------------------

    /**
     * The display method is called when the panel needs to be redrawn.
     * The is where the code goes for drawing the image, using OpenGL commands.
     */
    public void display(GLAutoDrawable drawable) {    
        
        GL2 gl2 = drawable.getGL().getGL2(); // The object that contains all the OpenGL methods.
         
        gl2.glClear( GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
        
        gl2.glLoadIdentity();             // Set up modelview transform. 
        gl2.glRotated(rotateZ,0,0,1);
        gl2.glRotated(rotateY,0,1,0);
        gl2.glRotated(rotateX,1,0,0);
        gl2.glScaled(scalex,scaley,scalez);
        gl2.glTranslated(moveX,moveY,0);

        cube(gl2,.5);
        /**
         * Shape vectors
         */
        double[][] houseVertexList =
                {{0,1,-.5}, {-.75,.25,-.5}, {-.75,-.75,-.5}, {.75,-.75,-.5}, {.75,.25,-.5},
                        {0,1,.5}, {-.75,.25,.5}, {-.75,-.75,.5}, {.75,-.75,.5}, {.75,.25,.5}};

        double[][] crossVertexList =
                { {-3,0,-.5},{-3,-.5,-.5}, {-2.5,-.5,-.5}, {-2.5,0,-.5}, {-2,0,-.5}, {-2,.5,-.5},
                {-2.5,.5,-.5}, {-2.5,1,-.5}, {-3,1,-.5}, {-3,.5,-.5}, {-3.5,.5,-.5}, {-3.5,0,-.5},
                {-3,0,.5},{-3,-.5,.5}, {-2.5,-.5,.5}, {-2.5,0,.5}, {-2,0,.5}, {-2,.5,.5},
                {-2.5,.5,.5}, {-2.5,1,.5}, {-3,1,.5}, {-3,.5,.5}, {-3.5,.5,.5}, {-3.5,0,.5}};

        double[][] letterLVertexList = {{3,1.5,-.5}, {4,1.5,-.5}, {4,2,-.5}, {3.5,2,-.5}, {3.5,2.5,-.5},{3,2.5,-.5},
                {3,1.5,.5}, {4,1.5,.5}, {4,2,.5}, {3.5,2,.5}, {3.5,2.5,.5},{3,2.5,.5}};

        double[][] triangleVertexList = {{0,1.5,-.1}, {2.25,1.5,-1}, {1.125,3,0}, {2.25,1.5,1}, {0,1.5,1}};


        double[][] diamondVertexList = {{-.5,1.75,0}, {-.25,2,0}, {-.25,2.25,0}, {-.5,2.5,0}, {-.75,2.25,0}, {-.75,2,0},
                {-.5,2.125,-.5},{-.5,2.125,.5}};

        double[][] parallelVertexList = {{2,.5,-1}, {3,.5,-1}, {3.25,1,-1}, {2.25,1,-1}, {4,.75,0}};

        int[][] houseFaceList = {{0,1,2,3,4}, {3,8,9,4}, {8,7,6,5,9}, {7,6,1,2}, {1,6,5,0}, {0,4,9,5}};
        int[][] crossFaceList = {{0,1,2,3,4,5,6,7,8,9,10,11}, {12,13,14,15,16,17,18,19,20,21,22,23},{11,0,12,23},
                {0,1,13,12}, {1,13,14,2}, {2,3,15,14}, {4,3,15,16},{4,16,17,5},
                {5,17,18,6}, {6,18,19,7}, {19,20,8,7}, {8,20,21,9}, {21,22,10,9}, {10,22,23,11}, {23,11,0,12}};
        int[][] letterLFaceList = {{0,1,2,3,4,5}, {6,7,8,9,10,11}, {0,6,1,7}, {1,7,8,2}, {2,8,9,3}, {9,10,4,3}, {10,4,5,11}, {11,5,0,6}};
        int[][] triangleFaceList = {{0,1,2}, {1,3,2}, {3,4,2}, {4,0,2}, {0,1,3,4}};
        int[][] diamondFaceList = {{0,1,6}, {1,6,2}, {2,6,3}, {3,4,6}, {4,6,5}, {5,6,0}, {0,1,7}, {1,2,7}, {2,3,7}, {3,4,7},{4,5,7},{5,0,7}};
        int[][] parallelFaceList = {{0,1,2,3}, {0,1,4}, {1,2,4}, {2,3,4}, {3,0,4}};


        /**
         * DRAW THE SHAPE ON THE SCREEN
         */
        createShape(gl2, houseVertexList,houseFaceList);
        createShape(gl2, crossVertexList,crossFaceList);
        createShape(gl2, letterLVertexList,letterLFaceList);
        createShape(gl2, triangleVertexList,triangleFaceList);
        createShape(gl2, diamondVertexList,diamondFaceList);
        createShape(gl2, parallelVertexList,parallelFaceList);
   	
        
    } // end display()

    public void init(GLAutoDrawable drawable) {
           // called when the panel is created
        GL2 gl2 = drawable.getGL().getGL2();
        gl2.glMatrixMode(GL2.GL_PROJECTION);
         gl2.glOrtho(-5, 5 ,-5, 5, -5, 5);
        gl2.glMatrixMode(GL2.GL_MODELVIEW);
        gl2.glClearColor( 0, 0, 0, 1 );
        gl2.glEnable(GL2.GL_DEPTH_TEST);
    }

    public void dispose(GLAutoDrawable drawable) {
            // called when the panel is being disposed
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
            // called when user resizes the window
    }
    
    // ----------------  Methods from the KeyListener interface --------------

    /**
     *  Rotations can be applied with the arrow keys. the page up
     *  key, and the page down key will zoom in and out.  The home key will set
     *  all rotations and placement to 0, and scale to 1.  Initial rotations about the
     *  x, y, and z axes are 15, -15, and 0. A D W S keys will translate the shapes to the side or up and down.
     * @param evt
     */
    public void keyPressed(KeyEvent evt) {
        int key = evt.getKeyCode();
        if ( key == KeyEvent.VK_LEFT )
            rotateY -= 15;
         else if ( key == KeyEvent.VK_RIGHT )
            rotateY += 15;
         else if ( key == KeyEvent.VK_DOWN)
            rotateX += 15;
         else if ( key == KeyEvent.VK_UP )
            rotateX -= 15;
         else if ( key == KeyEvent.VK_PAGE_UP ) {
            scalex += .25;
            scaley += .25;
            scalez += .25;
        }
         else if ( key == KeyEvent.VK_PAGE_DOWN ) {
            if (scalex > 0.25) {
                scalex -= .25;
                scaley -= .25;
                scalez -= .25;
            }else{
                scalex = scaley = scalez = .25;
            }
        }else if ( key == KeyEvent.VK_HOME ) {
            rotateX = rotateY = rotateZ = moveX = moveY = 0;
            scalex = scaley = scalez = 1;
        }
        else if ( key == KeyEvent.VK_A )
            moveX -= .25;
        else if ( key == KeyEvent.VK_D )
            moveX += .25;
        else if ( key == KeyEvent.VK_W )
            moveY += .25;
        else if ( key == KeyEvent.VK_S )
            moveY -= .25;

        repaint();
    }

    public void keyReleased(KeyEvent evt) {
    }
    
    public void keyTyped(KeyEvent evt) {
    }

    /**
     * this method will allow the program to color each side of the shapes
     * @param gl2
     * @param index
     */
    public void faceColor(GL2 gl2, int index){
        int faceColor = index%5;
        if (faceColor == 0){
            gl2.glColor3f(1,0,0);
        }else if(faceColor == 1){
            gl2.glColor3f(0,1,0);
        }else if(faceColor == 2){
            gl2.glColor3f(0,0,1);
        }else if(faceColor == 3){
            gl2.glColor3f(0,1,1);
        }else if(faceColor == 4){
            gl2.glColor3f(1,1,0);
        }
    }

    /**
     * this program will be responsible for drawing the shapes using the shape vectors.
     * @param gl2
     * @param vertexList
     * @param faceList
     */
    public void createShape(GL2 gl2, double[][] vertexList, int[][] faceList){
        for (int i = 0; i < faceList.length; i++) {
            faceColor(gl2, i);
            gl2.glBegin(GL2.GL_TRIANGLE_FAN);

            for (int j = 0; j < faceList[i].length; j++) {
                int vertexNum = faceList[i][j];  // Index for vertex j of face i.
                double[] vertexCoords = vertexList[vertexNum];  // The vertex itself.
                gl2.glVertex3dv( vertexCoords, 0 );
            }
            gl2.glEnd();
        }
    }
    
}
