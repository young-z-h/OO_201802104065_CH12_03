//201802104065闫天意
public class OverlapUtil {
    public static boolean checkOverlap(
            CanAttack canAttack, OverlapSensitive overlapped){
        int cx1 = canAttack.getCx();
        int cy1 = canAttack.getCy();
        int cx2 = overlapped.getCx();
        int cy2 = overlapped.getCy();


        int w1 = canAttack.getW();
        int h1 = canAttack.getH();
        int w2 = overlapped.getW();
        int h2 = overlapped.getH();

        int dx = Math.abs(cx1 - cx2);
        int dy = Math.abs(cy1 - cy2);
        return (dx < (w1 + w2)/2 && dy < (h1 + h2)/2);
    }
}
