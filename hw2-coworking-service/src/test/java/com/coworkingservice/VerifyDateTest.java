//package com.coworkingservice;
//
//import com.coworkingservice.entity.Slot;
//import com.coworkingservice.service.verify.VerifyDate;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import static org.mockito.Mockito.when;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.time.LocalDateTime;
//import java.util.List;
//@RunWith(MockitoJUnitRunner.class)
//public class VerifyDateTest {
//    private List<Slot> memoryDB = MemoryDB.getInstance().getReservedSlotListTable();
//    @Mock
//    private static Slot slot;
//    private VerifyDate verifyDate = new VerifyDate();
//    @Before
//    public void setUp() throws Exception {
//        memoryDB.add(slot);
//    }
//    @Test
//    public void checkDateTrueTest(){
//        when(slot.getFromLocalDateTime())
//                .thenReturn(LocalDateTime.parse("2024-06-25T12:00"));
//        when(slot.getToLocalDateTime())
//                .thenReturn(LocalDateTime.parse("2024-06-25T14:00"));
//        boolean verifyResult = verifyDate.checkDate(LocalDateTime.parse("2024-06-25T10:00"),
//                LocalDateTime.parse("2024-06-25T12:00"));
//        Assertions.assertTrue(verifyResult);
//    }
//    @Test
//    public void checkDateFalseTest(){
//        when(slot.getFromLocalDateTime())
//                .thenReturn(LocalDateTime.parse("2024-06-25T12:00"));
//        when(slot.getToLocalDateTime())
//                .thenReturn(LocalDateTime.parse("2024-06-25T14:00"));
//        boolean verifyResult = verifyDate.checkDate(LocalDateTime.parse("2024-06-25T10:00"),
//                LocalDateTime.parse("2024-06-25T13:00"));
//        Assertions.assertFalse(verifyResult);
//    }
//    @After
//    public void tearDown() throws Exception {
//        memoryDB.clear();
//    }
//}
